package com.vt.fish.service;

import com.vt.fish.model.request.VibrantTropicalOrderRequest;
import com.vt.fish.utility.StringUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Service
public class VibrantTropicalService {

    private final DatabaseService databaseService;

    public VibrantTropicalService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public ResponseEntity<String> serviceVibrantTropicalRequest (VibrantTropicalOrderRequest vibrantTropicalOrderRequest,BindingResult bindingResult) {
        databaseService.saveVibrantTropicalOrderRequest(vibrantTropicalOrderRequest);
        if(bindingResult.hasErrors()){
            List<String> messages = new ArrayList<>();
            for(FieldError fieldError: bindingResult.getFieldErrors())
            {
                messages.add(StringUtility.splitCamelCase(fieldError.getField()) + ": " + fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(messages.toString(),HttpStatus.BAD_REQUEST);
        }
        // Enhanced input validation
        // async Roadie call, store outbound inbound
        // async Payment call, store outbound inbound
        // async Tax call, store outbound inbound
        // notify Tyler
        // Store vibResponse
        return new ResponseEntity<>("",HttpStatus.CREATED);

    }
}
