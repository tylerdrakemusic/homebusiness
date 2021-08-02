package com.vt.fish.service;

import com.vt.fish.model.request.VibrantTropicalOrderRequest;
import com.vt.fish.model.response.SubordinateResponse;
import com.vt.fish.model.roadierequest.EstimateRequest;
import com.vt.fish.model.roadierequest.ShipmentRequest;
import com.vt.fish.model.roadieresponse.EstimateResponse;
import com.vt.fish.model.roadieresponse.ShipmentResponse;
import com.vt.fish.repository.*;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;

@Service
public record DatabaseService(
        VibrantTropicalOrderRequestRepository vibrantTropicalOrderRequestRepository,
        EstimateRequestRepository estimateRequestRepository, EstimateResponseRepository estimateResponseRepository,
        ShipmentRequestRepository shipmentRequestRepository, ShipmentResponseRepository shipmentResponseRepository,
        SubordinateResponseRepository subordinateResponseRepository) {

    public void saveVibrantTropicalOrderRequest(VibrantTropicalOrderRequest vibrantTropicalOrderRequest) {
        vibrantTropicalOrderRequest.setTimeStamp(GregorianCalendar.getInstance().getTime());
        vibrantTropicalOrderRequestRepository.save(vibrantTropicalOrderRequest);
    }

    public void saveEstimateRequest(EstimateRequest estimateRequest) {
        estimateRequest.setTimeStamp(GregorianCalendar.getInstance().getTime());
        estimateRequestRepository.save(estimateRequest);
    }

    public void saveSubordinateResponse(SubordinateResponse subordinateResponse){
        subordinateResponse.setTimeStamp(GregorianCalendar.getInstance().getTime());
        subordinateResponseRepository.save(subordinateResponse);
    }

    public void saveEstimateResponse(EstimateResponse estimateResponse) {
        estimateResponse.setTimeStamp(GregorianCalendar.getInstance().getTime());
        estimateResponseRepository.save(estimateResponse);
    }

    public void saveShipmentRequest(ShipmentRequest shipmentRequest) {
        shipmentRequest.setTimeStamp(GregorianCalendar.getInstance().getTime());
        shipmentRequestRepository.save(shipmentRequest);
    }

    public void saveShipmentResponse(ShipmentResponse shipmentResponse){
        shipmentResponse.setTimeStamp(GregorianCalendar.getInstance().getTime());
        shipmentResponseRepository.save(shipmentResponse);
    }
}
