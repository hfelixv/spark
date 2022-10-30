package com.spark.ana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableFeignClients - It's managed by ProjectConfig "FeignClient" configuration class
public class AnaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnaApplication.class, args);
    }

}