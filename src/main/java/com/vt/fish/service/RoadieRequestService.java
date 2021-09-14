package com.vt.fish.service;

import com.vt.fish.config.PlasticBaggingConfig;
import com.vt.fish.config.RoadieRequestServiceConfig;
import com.vt.fish.model.request.Product;
import com.vt.fish.model.request.VibrantTropicalOrderRequest;
import com.vt.fish.model.roadierequest.*;
import com.vt.fish.model.roadieresponse.EstimateResponse;
import com.vt.fish.model.roadieresponse.RoadieErrorResponse;
import com.vt.fish.model.roadieresponse.ShipmentResponse;
import com.vt.fish.utility.DateUtility;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
public class RoadieRequestService {

    private final RoadieRequestServiceConfig roadieRequestServiceConfig;
    private final PlasticBaggingConfig plasticBaggingConfig;
    private static final String NAME = "name";
    private static final String PERBAG = "perbag";
    private static final String LENGTH = "length";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String WEIGHT = "weight";

    public RoadieRequestService(RoadieRequestServiceConfig roadieRequestServiceConfig, PlasticBaggingConfig plasticBaggingConfig) {
        this.roadieRequestServiceConfig = roadieRequestServiceConfig;
        this.plasticBaggingConfig = plasticBaggingConfig;
    }

    private ArrayList<RoadieItem> buildBaggingItems(ArrayList<Product> products) {
        ArrayList<RoadieItem> roadieItemArrayList = new ArrayList<>();
        for (Product product : products) {
            for (Map<String, Object> map : plasticBaggingConfig.getProps()) {
                if (product.getProductName().equalsIgnoreCase((String) map.get(NAME))) {
                    int perbag = (Integer) map.get(PERBAG);
                    for (int i = 0; i < product.getQuantity(); i += perbag) {
                        int quantity = product.getQuantity() - i < perbag ? product.getQuantity() % perbag : perbag;
                        roadieItemArrayList.add(new RoadieItem(roadieRequestServiceConfig.getProductDescription(), product.getProductName(),
                                product.getDollars() * quantity, quantity, (Integer) map.get(LENGTH),
                                (Integer) map.get(WIDTH), (Integer) map.get(HEIGHT), (Integer) map.get(WEIGHT)));
                    }
                }
            }
        }
        return roadieItemArrayList;
    }

    public EstimateRequest buildEstimateRequest(VibrantTropicalOrderRequest vibrantTropicalOrderRequest) {
        ArrayList<RoadieItem> roadieItemArrayList = buildBaggingItems(vibrantTropicalOrderRequest.getProducts());


        RoadieAddress pickupAddress = new RoadieAddress(roadieRequestServiceConfig.getPickupName(), "0",
                roadieRequestServiceConfig.getPickupStreet(), roadieRequestServiceConfig.getPickupStreet2(),
                roadieRequestServiceConfig.getPickupCity(), roadieRequestServiceConfig.getPickupState(),
                roadieRequestServiceConfig.getPickupZip(), null, null);
        RoadieContact pickupContact = new RoadieContact(roadieRequestServiceConfig.getPickupName(), roadieRequestServiceConfig.getPickupPhoneNumber());
        RoadieLocation pickupLocation = new RoadieLocation(pickupAddress, pickupContact);

        RoadieAddress deliveryAddress = new RoadieAddress(vibrantTropicalOrderRequest.getShippingName(), null, vibrantTropicalOrderRequest.getShippingAddress(), vibrantTropicalOrderRequest.getShippingAddress2(), vibrantTropicalOrderRequest.getShippingCity(), vibrantTropicalOrderRequest.getShippingState(), vibrantTropicalOrderRequest.getShippingZip(), null, null);
        RoadieContact deliveryContact = new RoadieContact(vibrantTropicalOrderRequest.getShippingName(), vibrantTropicalOrderRequest.getShippingPhone());
        RoadieLocation deliveryLocation = new RoadieLocation(deliveryAddress, deliveryContact);

        String pickupAfter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                .format(DateUtility.addMinutesToJavaUtilDate(vibrantTropicalOrderRequest.getTimeStamp(), 1));
        String end = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                .format(DateUtility.addHoursToJavaUtilDate(vibrantTropicalOrderRequest.getTimeStamp(), 2));
        RoadieTimeWindow roadieTimeWindow = new RoadieTimeWindow(pickupAfter, end);

        return new EstimateRequest(roadieItemArrayList, pickupLocation, deliveryLocation, pickupAfter, roadieTimeWindow, vibrantTropicalOrderRequest.getCorrelationId(), vibrantTropicalOrderRequest.getVibrantTropicalRequestId());
    }

    public ShipmentRequest buildShipmentRequest(VibrantTropicalOrderRequest vibrantTropicalOrderRequest) {
        ArrayList<RoadieItem> roadieItemArrayList = buildBaggingItems(vibrantTropicalOrderRequest.getProducts());
        StringBuilder description = new StringBuilder();
        for (Product product : vibrantTropicalOrderRequest.getProducts()) {
            description.append(product.getProductName()).append(" ");
        }
        description.append("live fish.");
        RoadieAddress pickupAddress = new RoadieAddress("Vibrant Tropical Home", "1",
                roadieRequestServiceConfig.getPickupStreet(), roadieRequestServiceConfig.getPickupStreet2(),
                roadieRequestServiceConfig.getPickupCity(), roadieRequestServiceConfig.getPickupState(),
                roadieRequestServiceConfig.getPickupZip(), null, null);
        RoadieContact pickupContact = new RoadieContact(roadieRequestServiceConfig.getPickupName(), roadieRequestServiceConfig.getPickupPhoneNumber());
        RoadieLocation pickupLocation = new RoadieLocation(pickupAddress, pickupContact);

        RoadieAddress deliveryAddress = new RoadieAddress(vibrantTropicalOrderRequest.getShippingName(), null, vibrantTropicalOrderRequest.getShippingAddress(), vibrantTropicalOrderRequest.getShippingAddress2(), vibrantTropicalOrderRequest.getShippingCity(), vibrantTropicalOrderRequest.getShippingState(), vibrantTropicalOrderRequest.getShippingZip(), null, null);
        RoadieContact deliveryContact = new RoadieContact(vibrantTropicalOrderRequest.getShippingName(), vibrantTropicalOrderRequest.getShippingPhone());
        RoadieLocation deliveryLocation = new RoadieLocation(deliveryAddress, deliveryContact);

        String pickupAfter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                .format(DateUtility.addMinutesToJavaUtilDate(vibrantTropicalOrderRequest.getTimeStamp(), 1));
        String end = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                .format(DateUtility.addHoursToJavaUtilDate(vibrantTropicalOrderRequest.getTimeStamp(), 2));
        RoadieTimeWindow roadieTimeWindow = new RoadieTimeWindow(pickupAfter, end);
        //todo pass in deliveryOptions
        RoadieDeliveryOptions roadieDeliveryOptions = new RoadieDeliveryOptions(true, false, false, 0);
        return new ShipmentRequest(vibrantTropicalOrderRequest.getCorrelationId(), vibrantTropicalOrderRequest.getCorrelationId(), null, null, description.toString(), roadieItemArrayList, pickupLocation, deliveryLocation, pickupAfter, roadieTimeWindow, roadieDeliveryOptions, vibrantTropicalOrderRequest.getVibrantTropicalRequestId());
    }

    //todo: test
    @Retryable(value = RuntimeException.class)
    public EstimateResponse makeEstimateRequest(EstimateRequest estimateRequest) {

        Mono<EstimateResponse> estimateResponseMono = WebClient.create()
                .post()
                .uri(URI.create(roadieRequestServiceConfig.getUrl() + roadieRequestServiceConfig.getEstimatePath()))
                .body(BodyInserters.fromValue(estimateRequest))
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, roadieRequestServiceConfig.getAuthorizationType() + " " + roadieRequestServiceConfig.getKey())
                .exchange()
                .timeout(Duration.ofSeconds(5))
                .flatMap(clientResponse -> {
                    //Error handling
                    if (clientResponse.statusCode() == HttpStatus.BAD_REQUEST) {

                        return clientResponse.bodyToMono(RoadieErrorResponse.class).flatMap(
                                roadieErrorResponse1 -> Mono.error(new RuntimeException(roadieErrorResponse1.toString()))
                        );
                    }
                    return clientResponse.bodyToMono(EstimateResponse.class);
                });
        return estimateResponseMono.block();
    }

    //todo: test
    @Retryable(value = RuntimeException.class)
    public CompletableFuture<ShipmentResponse> makeShipmentRequest(ShipmentRequest shipmentRequest) {
        return CompletableFuture.supplyAsync(() -> {
            Mono<ShipmentResponse> shipmentResponseMono = WebClient.create()
                    .post()
                    .uri(URI.create(roadieRequestServiceConfig.getUrl() + roadieRequestServiceConfig.getShipmentPath()))
                    .body(BodyInserters.fromValue(shipmentRequest))
                    .accept(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.AUTHORIZATION, roadieRequestServiceConfig.getAuthorizationType() + " " + roadieRequestServiceConfig.getKey())
                    .exchange()
                    .timeout(Duration.ofSeconds(5))
                    .flatMap(clientResponse -> {
                        //Error handling
                        if (clientResponse.statusCode() == HttpStatus.BAD_REQUEST) {

                            return clientResponse.bodyToMono(RoadieErrorResponse.class).flatMap(
                                    roadieErrorResponse1 -> Mono.error(new RuntimeException(roadieErrorResponse1.toString()))
                            );
                        }
                        return clientResponse.bodyToMono(ShipmentResponse.class);
                    });
            return shipmentResponseMono.block();
        });
    }
}
