package com.vt.fish.service;

import com.vt.fish.model.request.VibrantTropicalOrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VibrantTropicalService {

    public ResponseEntity serviceVibrantTropicalRequest (VibrantTropicalOrderRequest vibrantTropicalOrderRequest) {
        // Enhanced input validation
        // async Roadie call
        // async Payment call
        // async Tax call
        // notify Tyler
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
