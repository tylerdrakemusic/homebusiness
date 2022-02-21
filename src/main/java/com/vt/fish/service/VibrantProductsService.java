package com.vt.fish.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vt.fish.config.ProductConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VibrantProductsService {

    private ProductConfig productConfig;

    private ObjectMapper objectMapper;

    public VibrantProductsService(ProductConfig productConfig, ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
        this.productConfig = productConfig;
    }

    public ResponseEntity<String> serviceProducts() throws JsonProcessingException {
        return new ResponseEntity<>(objectMapper.writeValueAsString(productConfig.getProps()), HttpStatus.OK);
    }
}
