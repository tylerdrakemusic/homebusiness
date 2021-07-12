package com.vt.fish.model.roadierequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document(collection = "collection")
public class EstimateRequest {

    public EstimateRequest(ArrayList<RoadieItem> roadieItemList, RoadieLocation pickupLocation, RoadieLocation deliveryLocation, String pickupAfter, RoadieTimeWindow roadieTimeWindow, String correlationId, String vibrantTropicalRequestId) {
        this.roadieItemList = roadieItemList;
        this.pickupLocation = pickupLocation;
        this.deliveryLocation = deliveryLocation;
        this.pickupAfter = pickupAfter;
        this.roadieTimeWindow = roadieTimeWindow;
        this.correlationId = correlationId;
        this.vibrantTropicalRequestId = vibrantTropicalRequestId;
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

    private final String correlationId;

    private final String vibrantTropicalRequestId;

    private Date timeStamp;

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

    public String getCorrelationId() {
        return correlationId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
