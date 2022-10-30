package com.spark.ana.rest.client;


import com.spark.ana.rest.client.response.Item;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "alma",
             url = "${alma.service.url}")
@Headers("Accept: application/json")
public interface AlmaProxy {



    @Headers({
            "Accept: application/json"
            ,"Content-Type: application/json"
    })
    @RequestMapping(method = RequestMethod.GET, value = "/items?view=label&apikey=l7xx2af7939c63424511946e0fcdc35fe22a&item_barcode={key}",
                    produces = "application/json", consumes = "application/json")
    Item update1(@PathVariable(value="key") String key);


//    @Headers({
//            "Accept: application/json",
//            "Content-Type: application/json"
//    })
    @RequestMapping(method = RequestMethod.GET, value = "/items?view=label&apikey=l7xx2af7939c63424511946e0fcdc35fe22a&",
                    produces = "application/json"//, consumes = "application/json"
                   )
    Item almaRequest1(
            @RequestParam(value="item_barcode") String item_barcode);


}
