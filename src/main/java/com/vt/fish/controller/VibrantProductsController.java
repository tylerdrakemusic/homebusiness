package com.vt.fish.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.vt.fish.logging.annotation.VibrantLog;
import com.vt.fish.service.VibrantProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.UUID;

//todo: Add /health
@RestController
@RequestMapping(value = "/products")
public class VibrantProductsController {
    private VibrantProductsService vibrantProductsService;

    public VibrantProductsController(VibrantProductsService vibrantProductsService){
        this.vibrantProductsService = vibrantProductsService;
    }
    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @VibrantLog(before = "Fetching products", after = "Done fetching products")
    public ResponseEntity<String> getProducts(
        @RequestHeader(value = "CorrelationId") String correlationId, WebRequest webRequest) throws JsonProcessingException {
        webRequest.setAttribute("VibrantProductRequestId", UUID.randomUUID().toString(), 1);
        return vibrantProductsService.serviceProducts();
    }
}
