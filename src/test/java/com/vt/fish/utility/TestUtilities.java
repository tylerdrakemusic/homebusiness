package com.vt.fish.utility;

import com.vt.fish.model.request.ProductOrder;
import com.vt.fish.model.request.VibrantTropicalOrderRequest;
import com.vt.fish.model.roadierequest.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class TestUtilities {

    public static EstimateRequest buildEstimateRequestShell() {
        return new EstimateRequest(
                new ArrayList<>(),
                buildRoadieLocationShell(),
                buildRoadieLocationShell(),
                "",
                new RoadieTimeWindow("", ""),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString());
    }

    public static ShipmentRequest buildShipmentRequestShell() {
        return new ShipmentRequest(
                "", "", "", "", "",
                new ArrayList<>(),
                buildRoadieLocationShell(),
                buildRoadieLocationShell(),
                "",
                new RoadieTimeWindow("", ""),
                new RoadieDeliveryOptions(true, true, true, 0.0),
                UUID.randomUUID().toString()
        );
    }

    private static RoadieLocation buildRoadieLocationShell() {
        return new RoadieLocation(
                new RoadieAddress(
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        0.0, 0.0),
                new RoadieContact("", ""));
    }

    public static VibrantTropicalOrderRequest buildVibrantTropicalOrderRequestSample() {
        VibrantTropicalOrderRequest vibrantTropicalOrderRequest = new VibrantTropicalOrderRequest();

        vibrantTropicalOrderRequest.setCorrelationId(UUID.randomUUID().toString());

        vibrantTropicalOrderRequest.setShippingName("Tom");
        vibrantTropicalOrderRequest.setShippingAddress("6674 S Prescott Way");
        vibrantTropicalOrderRequest.setShippingAddress2("Front Door");
        vibrantTropicalOrderRequest.setShippingCity("Littleton");
        vibrantTropicalOrderRequest.setShippingState("CO");
        vibrantTropicalOrderRequest.setShippingZip("80134");

        vibrantTropicalOrderRequest.setTimeStamp(new Date());

        vibrantTropicalOrderRequest.setProducts(buildProductsSample());
        return vibrantTropicalOrderRequest;
    }

    private static ArrayList<ProductOrder> buildProductsSample() {
        ArrayList<ProductOrder> productOrders = new ArrayList<>();
        productOrders.add(new ProductOrder(3, "Bronco Guppy Male", 9, "Trio"));
        productOrders.add(new ProductOrder(3, "Red Wagtail Platy", 7, "Happy"));
        return productOrders;
    }
}
