package com.vt.fish.service;

import com.vt.fish.model.request.VibrantTropicalOrderRequest;
import com.vt.fish.model.roadierequest.EstimateRequest;
import com.vt.fish.model.roadieresponse.EstimateResponse;
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
    private final RoadieRequestService roadieRequestService;

    public VibrantTropicalService(DatabaseService databaseService, RoadieRequestService roadieRequestService) {
        this.databaseService = databaseService;
        this.roadieRequestService = roadieRequestService;
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
            //Credit card validation

        // Clone shipping
        if(vibrantTropicalOrderRequest.getSameAddress().equals("on")){
            cloneShipping(vibrantTropicalOrderRequest);
        }
        // Build Estimate
        EstimateRequest estimateRequest = roadieRequestService.buildEstimateRequest(vibrantTropicalOrderRequest);
        //Store estimate request
        EstimateResponse estimateResponse = roadieRequestService.makeEstimateRequest(estimateRequest);
        //Store estimate response

        // async Roadie call, store outbound inbound
        // async Payment call, store outbound inbound
        // async Tax call, store outbound inbound
        // Store vibResponse
        // notify Tyler
        return new ResponseEntity<>("",HttpStatus.CREATED);
    }

    private void cloneShipping(VibrantTropicalOrderRequest vibrantTropicalOrderRequest) {
        vibrantTropicalOrderRequest.setShippingName(vibrantTropicalOrderRequest.getBillingName());
        vibrantTropicalOrderRequest.setShippingPhone(vibrantTropicalOrderRequest.getBillingPhone());
        vibrantTropicalOrderRequest.setShippingAddress(vibrantTropicalOrderRequest.getBillingAddress());
        vibrantTropicalOrderRequest.setShippingAddress2(vibrantTropicalOrderRequest.getBillingAddress2());
        vibrantTropicalOrderRequest.setShippingCity(vibrantTropicalOrderRequest.getBillingCity());
        vibrantTropicalOrderRequest.setShippingState(vibrantTropicalOrderRequest.getBillingState());
        vibrantTropicalOrderRequest.setShippingZip(vibrantTropicalOrderRequest.getBillingZip());
    }
}
