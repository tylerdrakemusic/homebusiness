package com.vt.fish.service;

import com.vt.fish.controller.special.OrderOutOfRangeException;
import com.vt.fish.model.request.VibrantTropicalOrderRequest;
import com.vt.fish.model.roadierequest.EstimateRequest;
import com.vt.fish.model.roadierequest.ShipmentRequest;
import com.vt.fish.model.roadieresponse.EstimateResponse;
import com.vt.fish.model.roadieresponse.ShipmentResponse;
import com.vt.fish.utility.StringUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class VibrantTropicalService {

    private final DatabaseService databaseService;
    private final RoadieRequestService roadieRequestService;

    public VibrantTropicalService(DatabaseService databaseService, RoadieRequestService roadieRequestService) {
        this.databaseService = databaseService;
        this.roadieRequestService = roadieRequestService;
    }

    public ResponseEntity<String> serviceVibrantTropicalRequest(VibrantTropicalOrderRequest vibrantTropicalOrderRequest, BindingResult bindingResult) {
        //todo: SetupLogging
        databaseService.saveVibrantTropicalOrderRequest(vibrantTropicalOrderRequest);
        if (bindingResult.hasErrors()) {
            List<String> messages = new ArrayList<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                messages.add(StringUtility.splitCamelCase(fieldError.getField()) + ": " + fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(messages.toString(), HttpStatus.BAD_REQUEST);
        }

        //todo: credit card validation

        massageRequest(vibrantTropicalOrderRequest);

        EstimateRequest estimateRequest = roadieRequestService.buildEstimateRequest(vibrantTropicalOrderRequest);
        databaseService.saveEstimateRequest(estimateRequest);
        EstimateResponse estimateResponse = roadieRequestService.makeEstimateRequest(estimateRequest);

        estimateResponse.setCorrelationId(vibrantTropicalOrderRequest.getCorrelationId());
        estimateResponse.setVibrantTropicalRequestId(vibrantTropicalOrderRequest.getVibrantTropicalRequestId());
        databaseService.saveEstimateResponse(estimateResponse);

        //todo: outOfDeliveryRange logic
        if(Double.parseDouble(estimateResponse.getPrice()) > vibrantTropicalOrderRequest.getTotalOrderPrice()){
            throw new OrderOutOfRangeException("Order cost efficiency invalid.  Try a pick up location close to Lincoln & Chambers in Parker, Colorado or by ordering more product.");
        }

        ShipmentRequest shipmentRequest = roadieRequestService.buildShipmentRequest(vibrantTropicalOrderRequest);
        databaseService.saveShipmentRequest(shipmentRequest);
        CompletableFuture <ShipmentResponse> shipmentResponse = roadieRequestService.makeShipmentRequest(shipmentRequest);
        // todo: async Stripe Payment call, store outbound inbound
        // todo: async Tax call, store outbound inbound

        CompletableFuture<Void> allCompleted = CompletableFuture.allOf(shipmentResponse);
        allCompleted.join();
        try {
            databaseService.saveShipmentResponse(shipmentResponse.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("3rd Party Calls Failed");
        }

        // todo: notify Tyler
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }


    private void massageRequest(VibrantTropicalOrderRequest vibrantTropicalOrderRequest) {
        if (vibrantTropicalOrderRequest.getProducts() == null) {
            vibrantTropicalOrderRequest.setProducts(new ArrayList<>());
        }
        cloneShipping(vibrantTropicalOrderRequest);
    }

    private void cloneShipping(VibrantTropicalOrderRequest vibrantTropicalOrderRequest) {
        if (!"off".equals(vibrantTropicalOrderRequest.getSameAddress())) {
            vibrantTropicalOrderRequest.setShippingName(vibrantTropicalOrderRequest.getBillingName());
            vibrantTropicalOrderRequest.setShippingPhone(vibrantTropicalOrderRequest.getBillingPhone());
            vibrantTropicalOrderRequest.setShippingAddress(vibrantTropicalOrderRequest.getBillingAddress());
            vibrantTropicalOrderRequest.setShippingAddress2(vibrantTropicalOrderRequest.getBillingAddress2());
            vibrantTropicalOrderRequest.setShippingCity(vibrantTropicalOrderRequest.getBillingCity());
            vibrantTropicalOrderRequest.setShippingState(vibrantTropicalOrderRequest.getBillingState());
            vibrantTropicalOrderRequest.setShippingZip(vibrantTropicalOrderRequest.getBillingZip());
        }
    }
}
