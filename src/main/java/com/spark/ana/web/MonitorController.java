package com.spark.ana.web;


//import lombok.extern.slf4j.Slf4j;
import com.spark.ana.request.TagRequest;
//import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/monitor")

public class MonitorController {

    @PostMapping(value = "/connect", produces = "application/json")
    public ResponseEntity<String> verifyLoan(@RequestBody TagRequest request) {
//        log.info(request.toString());
//        if(!request.validate("NEW")){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Empty parameters are not allowed please review request");
//        }
//        Long createdId = savedSearchService.saveSearch(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("request received");
    }
}
