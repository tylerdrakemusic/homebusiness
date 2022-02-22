package com.vt.fish.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vt.fish.config.ProductConfig;
import com.vt.fish.model.product.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VibrantProductsService {

    private DatabaseService databaseService;

    public VibrantProductsService(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    public ResponseEntity<List<Product>> serviceProducts() {
        List<Product> products = databaseService.fetchProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
