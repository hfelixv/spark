package com.spark.ana.service;

//import com.impinj.octane.*;
import com.impinj.octane.*;
import com.spark.ana.properties.SampleProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Scanner;

    /**
     * Connect to GPO device to use port 3 for
     * 1.- turn it on by 1 second
     * 2.- sleep by 3 second
     * 3.- loop it for 5 times
     */
    @Slf4j
    @Service
    public class GPOConnService {

    public static void main(String[] args) {
        GPOConnService gpoConnService = new GPOConnService();
        String result = gpoConnService.triggerGPOOn();
    }

    public String triggerGPOOn() {
        String result = "success";
        try {
            //agarra el hostname, la ip address de la antena
            //e.g. speedwayr-11-49-77
//            String hostname = System.getProperty(SampleProperties.hostname);
            String hostname = SampleProperties.readerIpAddress;

            if (hostname == null) {
                throw new Exception("Must specify the '" + SampleProperties.hostname + "' property");
            }

            //instancia un reader
            ImpinjReader reader = new ImpinjReader();

            // Connect
            System.out.println("Connecting to " + hostname);
            reader.connect(hostname);

            //instancia las features
            FeatureSet features = reader.queryFeatureSet();

            if (features.getGpoCount() < 4) {
                System.out.print("Must use a reader with at least 4 GPOs to "
                        + "run this example");
                System.exit(-2);
            }

            // Get the default settings, del reader
            Settings settings = reader.queryDefaultSettings();

            //el objeto gpo es una "REFERENCIA" al settings y se usa
            //para configurar las 4 salidas del dispositivo GPO
            GpoConfigGroup gpos = settings.getGpos();

            // this gpo will be high when tags inventory is running
            gpos.getGpo((short) 1).setMode(GpoMode.ReaderInventoryStatus);

            // this will go high when a client app connects
            gpos.getGpo((short) 2).setMode(GpoMode.LLRPConnectionStatus);

            // this will pulse for a period of time
            gpos.getGpo((short) 3).setMode(GpoMode.Pulsed);
            gpos.getGpo((short) 3).setGpoPulseDurationMsec(1000);

            // just a normal GPO
            gpos.getGpo((short) 4).setMode(GpoMode.Normal);

            // Apply the new settings al reader
            reader.applySettings(settings);

            // disconnect and reconnect to show GPO
            System.out.println("Disconnecting from " + hostname);
            reader.disconnect();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                // ignore this since its just an example and just keep going
            }
            System.out.println("Connecting to " + hostname);
            reader.connect(hostname);

            // delay so we can see the GPO state
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                // ignore this since its just an example and just keep going
            }

            // don't connect a listener so we dont get the reports
            // typical applications will still connect a tag listener
            // reader.setTagReportListener(new TagReportListenerImplementation());

            // Start the reader
            System.out.println("Starting inventory on " + hostname);
            reader.start();

            // Set the GPO high, every three seconds.
            // The GPO will remain high for the period
            // specified by GpoPulseDurationMsec en la linea 51
            //solo le da 5 pasadas, en cada una
            //prende el puerto 3 por un segundo y se duerme tres segundos
            for (int i = 0; i < 5; i++) {
                //(int port, boolean portState)
                reader.setGpo(3, true); //<-- aqui nosotros prenderemos la alarma!
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    // ignore this since its just an example and just keep going
                }
            }

            System.out.println("Press Enter to exit.");
            Scanner s = new Scanner(System.in);
            s.nextLine();
            //lo para
            System.out.println("Stopping  " + hostname);
            reader.stop();
            //lo desconecta
            System.out.println("Disconnecting from " + hostname);
            reader.disconnect();

            System.out.println("Done");
        } catch (OctaneSdkException ex) {
            System.out.println("OctaneSdkException = " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("HFV Exception = " + ex.getMessage());
//            ex.printStackTrace(System.out);
            System.out.println("NO hay impinj reader conectado :(");
        }

        return result;
    }
}