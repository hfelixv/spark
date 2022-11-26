package com.spark.ana.web;


//import lombok.extern.slf4j.Slf4j;
import com.spark.ana.request.TagRequest;
//import org.springdoc.api.annotations.ParameterObject;
import com.spark.ana.rest.client.response.Item;
//import com.spark.ana.service.GPOConnService;
import com.spark.ana.service.GPOConnService;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;

import com.spark.ana.rest.client.AlmaProxy;
import feign.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;



import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/monitor")
@Slf4j
public class MonitorController {

    @Autowired
    private AlmaProxy proxy;
    @Autowired
    private GPOConnService gpoConnService;


    @PostMapping(value = "/connect", produces = "application/json")
    public ResponseEntity<String> verifyLoan(@Valid @ParameterObject TagRequest request) {
        Item item = null;


//        poner llamada
//        https://api-na.hosted.exlibrisgroup.com/almaws/v1/bibs/almaws/v1/bibs/99939650000541/holdings/224075410000541/items/234075400000541/loans?apikey=l7xx2af7939c63424511946e0fcdc35fe22a

//         if (request.getOption() == 1L) {
//             System.out.println("opcion 1");
//             item = proxy.request1("42737");
//         } else
         if (request.getOption() == 2L){
             System.out.println("opcion 222222222");
             item = proxy.update1("42737");

         }
         else {
             System.out.println("opcion 33");
             item = proxy.almaRequest1("42737");

         }


        return ResponseEntity.status(HttpStatus.OK).body(item.toString());


    }

    @GetMapping(value = "/trigger", produces = "application/json")
    public ResponseEntity<String> triggerGPOOn(@Valid @ParameterObject TagRequest request) {
        String result = "hfv:success";


        result = gpoConnService.triggerGPOOn();


        return ResponseEntity.status(HttpStatus.OK).body(result.toString());


    }
}
