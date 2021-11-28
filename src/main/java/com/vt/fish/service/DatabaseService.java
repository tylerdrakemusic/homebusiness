package com.vt.fish.service;

import com.vt.fish.logging.VibrantLogger;
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
        SubordinateResponseRepository subordinateResponseRepository, VibrantLogger vibrantLogger) {

    public void saveVibrantTropicalOrderRequest(VibrantTropicalOrderRequest vibrantTropicalOrderRequest) {
        vibrantLogger.info("Persisting Vibrant Tropical request " + vibrantTropicalOrderRequest.getVibrantTropicalRequestId());
        vibrantTropicalOrderRequest.setTimeStamp(GregorianCalendar.getInstance().getTime());
        vibrantTropicalOrderRequestRepository.save(vibrantTropicalOrderRequest);
    }

    public void saveEstimateRequest(EstimateRequest estimateRequest) {
        vibrantLogger.info("Persisting estimate request");
        estimateRequest.setTimeStamp(GregorianCalendar.getInstance().getTime());
        estimateRequestRepository.save(estimateRequest);
    }

    public void saveSubordinateResponse(SubordinateResponse subordinateResponse){
        vibrantLogger.info("Persisting subordinate response");
        subordinateResponse.setTimeStamp(GregorianCalendar.getInstance().getTime());
        subordinateResponseRepository.save(subordinateResponse);
    }

    public void saveEstimateResponse(EstimateResponse estimateResponse) {
        vibrantLogger.info("Persisting estimate response" );
        estimateResponse.setTimeStamp(GregorianCalendar.getInstance().getTime());
        estimateResponseRepository.save(estimateResponse);
    }

    public void saveShipmentRequest(ShipmentRequest shipmentRequest) {
        vibrantLogger.info("Persisting shipment request");
        shipmentRequest.setTimeStamp(GregorianCalendar.getInstance().getTime());
        shipmentRequestRepository.save(shipmentRequest);
    }

    public void saveShipmentResponse(ShipmentResponse shipmentResponse){
        vibrantLogger.info("Persisting shipment response");
        shipmentResponse.setTimeStamp(GregorianCalendar.getInstance().getTime());
        shipmentResponseRepository.save(shipmentResponse);
    }
}
