package com.vt.fish.controller;

import com.vt.fish.model.request.VibrantTropicalOrderRequest;
import com.vt.fish.service.VibrantTropicalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/order")
public class VibrantTropicalController {
    private VibrantTropicalService vibrantTropicalService;

    public VibrantTropicalController(VibrantTropicalService vibrantTropicalService){
        this.vibrantTropicalService = vibrantTropicalService;
    }

    @CrossOrigin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity order(
            @RequestHeader(value = "CorrelationId") String correlationId,
            @RequestBody @Valid VibrantTropicalOrderRequest vibrantTropicalOrderRequest){

        return vibrantTropicalService.serviceVibrantTropicalRequest(vibrantTropicalOrderRequest);
    }
}