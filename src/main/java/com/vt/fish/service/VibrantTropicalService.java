package com.vt.fish.service;

import com.vt.fish.controller.special.OrderOutOfRangeException;
import com.vt.fish.logging.VibrantLogger;
import com.vt.fish.logging.annotation.EnableVibrantLogging;
import com.vt.fish.logging.annotation.VibrantLog;
import com.vt.fish.model.request.Product;
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
    private final VibrantLogger vibrantLogger;

    public VibrantTropicalService(DatabaseService databaseService, RoadieRequestService roadieRequestService, VibrantLogger vibrantLogger) {
        this.databaseService = databaseService;
        this.roadieRequestService = roadieRequestService;
        this.vibrantLogger = vibrantLogger;
    }

    @VibrantLog(before = "Servicing Vibrant Tropical request order", after = "Done servicing order", vibrantTropicalRequestId = "#{vibrantTropicalOrderRequest.vibrantTropicalRequestId}")
    public ResponseEntity<String> serviceVibrantTropicalRequest(VibrantTropicalOrderRequest vibrantTropicalOrderRequest, BindingResult bindingResult) {
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

        //todo: outOfDeliveryFishLifeRange logic
        if (estimateResponse.getPrice() != null) {
            if (Double.parseDouble(estimateResponse.getPrice()) > vibrantTropicalOrderRequest.getTotalOrderPrice()) {
                throw new OrderOutOfRangeException("Order efficiency invalid.  Try a pick up location close to Lincoln & Chambers in Parker, Colorado or by ordering more product.");
            }
        }

        ShipmentRequest shipmentRequest = roadieRequestService.buildShipmentRequest(vibrantTropicalOrderRequest);
        databaseService.saveShipmentRequest(shipmentRequest);
        CompletableFuture<ShipmentResponse> shipmentResponseFuture = roadieRequestService.makeShipmentRequest(shipmentRequest);

        // todo: async Stripe Payment call, store outbound inbound
        // todo: async Tax call, store outbound inbound

        CompletableFuture<Void> allCompleted = CompletableFuture.allOf(shipmentResponseFuture);
        allCompleted.join();

        try {
            ShipmentResponse shipmentResponse = shipmentResponseFuture.get();
            shipmentResponse.setCorrelationId(vibrantTropicalOrderRequest.getCorrelationId());
            shipmentResponse.setVibrantTropicalRequestId(vibrantTropicalOrderRequest.getVibrantTropicalRequestId());
            databaseService.saveShipmentResponse(shipmentResponse);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("3rd Party Calls Failed");
        }

        // todo: notify Tyler
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }


    //todo: test
    private void massageRequest(VibrantTropicalOrderRequest vibrantTropicalOrderRequest) {
        if (vibrantTropicalOrderRequest.getProducts() == null) {
            vibrantTropicalOrderRequest.setProducts(new ArrayList<>());
        }
        for (Product product : vibrantTropicalOrderRequest.getProducts()) {
            if (product.getSubProduct().toLowerCase().contains("pair")) {
                product.setQuantity(product.getQuantity() * 2);
            } else if (product.getSubProduct().toLowerCase().contains("trio")) {
                product.setQuantity(product.getQuantity() * 3);
            }
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
