package com.vt.fish.model.roadierequest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class EstimateRequest {

    public EstimateRequest(ArrayList<RoadieItem> roadieItemList, RoadieLocation pickupLocation, RoadieLocation deliveryLocation, String pickupAfter, RoadieTimeWindow roadieTimeWindow) {
        this.roadieItemList = roadieItemList;
        this.pickupLocation = pickupLocation;
        this.deliveryLocation = deliveryLocation;
        this.pickupAfter = pickupAfter;
        this.roadieTimeWindow = roadieTimeWindow;
    }

    @JsonProperty("items")
    private final ArrayList<RoadieItem> roadieItemList;

    @JsonProperty("pickup_location")
    private final RoadieLocation pickupLocation;

    @JsonProperty("delivery_location")
    private final RoadieLocation deliveryLocation;

    @JsonProperty("pickup_after")
    private final String pickupAfter;

    @JsonProperty("deliver_between")
    private final RoadieTimeWindow roadieTimeWindow;

    public ArrayList<RoadieItem> getRoadieItemList() {
        return roadieItemList;
    }

    public RoadieLocation getPickupLocation() {
        return pickupLocation;
    }

    public RoadieLocation getDeliveryLocation() {
        return deliveryLocation;
    }

    public String getPickupAfter() {
        return pickupAfter;
    }

    public RoadieTimeWindow getRoadieTimeWindow() {
        return roadieTimeWindow;
    }
}
