package com.vt.fish.service;

import com.vt.fish.logging.VibrantLogger;
import com.vt.fish.model.request.VibrantTropicalOrderRequest;
import com.vt.fish.model.response.SubordinateResponse;
import com.vt.fish.model.roadierequest.*;
import com.vt.fish.model.roadieresponse.EstimateResponse;
import com.vt.fish.model.roadieresponse.ShipmentResponse;
import com.vt.fish.repository.*;
import com.vt.fish.utility.TestUtilities;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

public class DatabaseServiceTest {
    private DatabaseService databaseService;
    @Mock
    private VibrantTropicalOrderRequestRepository vibrantTropicalOrderRequestRepository;
    @Mock
    private EstimateRequestRepository estimateRequestRepository;
    @Mock
    private EstimateResponseRepository estimateResponseRepository;
    @Mock
    private ShipmentRequestRepository shipmentRequestRepository;
    @Mock
    private ShipmentResponseRepository shipmentResponseRepository;
    @Mock
    private SubordinateResponseRepository subordinateResponseRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private VibrantLogger vibrantLogger;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        databaseService = new DatabaseService(vibrantTropicalOrderRequestRepository, estimateRequestRepository,
                estimateResponseRepository, shipmentRequestRepository, shipmentResponseRepository, subordinateResponseRepository, productRepository, vibrantLogger);
    }

    @Test
    public void vibrantTropicalOrderRequest_shouldSave() {
        VibrantTropicalOrderRequest vibrantTropicalOrderRequest = new VibrantTropicalOrderRequest();
        String requestId = vibrantTropicalOrderRequest.getVibrantTropicalRequestId();
        databaseService.saveVibrantTropicalOrderRequest(vibrantTropicalOrderRequest);
        verify(vibrantTropicalOrderRequestRepository).save(argThat(v -> requestId.equals(v.getVibrantTropicalRequestId())));
    }

    @Test
    public void estimateRequest_shouldSave() {
        EstimateRequest estimateRequest = TestUtilities.buildEstimateRequestShell();
        String requestId = estimateRequest.getVibrantTropicalRequestId();
        databaseService.saveEstimateRequest(estimateRequest);
        verify(estimateRequestRepository).save(argThat(e -> requestId.equals(e.getVibrantTropicalRequestId())));
    }

    @Test
    public void estimateResponse_shouldSave() {
        EstimateResponse estimateResponse = new EstimateResponse();
        String requestId = UUID.randomUUID().toString();
        estimateResponse.setVibrantTropicalRequestId(requestId);
        databaseService.saveEstimateResponse(estimateResponse);
        verify(estimateResponseRepository).save(argThat(e -> requestId.equals(e.getVibrantTropicalRequestId())));
    }

    @Test
    public void shipmentRequest_shouldSave() {
        ShipmentRequest shipmentRequest = TestUtilities.buildShipmentRequestShell();
        String requestId = shipmentRequest.getVibrantTropicalRequestId();
        databaseService.saveShipmentRequest(shipmentRequest);
        verify(shipmentRequestRepository).save(argThat(s -> requestId.equals(s.getVibrantTropicalRequestId())));
    }

    @Test
    public void shipmentResponse_shouldSave() {
        ShipmentResponse shipmentResponse = new ShipmentResponse();
        String requestId = UUID.randomUUID().toString();
        shipmentResponse.setVibrantTropicalRequestId(requestId);
        databaseService.saveShipmentResponse(shipmentResponse);
        verify(shipmentResponseRepository).save(argThat(s -> requestId.equals(s.getVibrantTropicalRequestId())));
    }

    @Test
    public void subOrdinateResponse_shouldSave() {
        SubordinateResponse subordinateResponse = new SubordinateResponse();
        String requestId = UUID.randomUUID().toString();
        subordinateResponse.setVibrantTropicalRequestId(requestId);
        databaseService.saveSubordinateResponse(subordinateResponse);
        verify(subordinateResponseRepository).save(argThat(s -> requestId.equals(s.getVibrantTropicalRequestId())));
    }

    @Test
    public void products_shouldBeFound(){
        databaseService.fetchProducts();
        verify(productRepository).findAll();
    }
}
