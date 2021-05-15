package com.vt.fish.controller;

import com.vt.fish.model.request.VibrantTropicalOrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/order")
public class VibrantTropicalController {
    @CrossOrigin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity order(
            @RequestHeader(value = "Sample Header") String sampleHeader,
            @RequestBody @Valid VibrantTropicalOrderRequest vibrantTropicalOrderRequest){
        System.out.println(vibrantTropicalOrderRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}