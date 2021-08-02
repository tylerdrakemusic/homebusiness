package com.vt.fish.model.roadierequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document(collection = "collection")
public class ShipmentRequest {

    public ShipmentRequest(String referenceId, String idempotencyKey, String alternateId1, String alternateId2, String description, ArrayList<RoadieItem> roadieItemList, RoadieLocation pickupLocation, RoadieLocation deliveryLocation, String pickupAfter, RoadieTimeWindow roadieTimeWindow, RoadieDeliveryOptions roadieDeliveryOptions) {
        this.referenceId = referenceId;
        this.idempotencyKey = idempotencyKey;
        this.alternateId1 = alternateId1;
        this.alternateId2 = alternateId2;
        this.description = description;
        this.roadieItemList = roadieItemList;
        this.pickupLocation = pickupLocation;
        this.deliveryLocation = deliveryLocation;
        this.pickupAfter = pickupAfter;
        this.roadieTimeWindow = roadieTimeWindow;
        this.roadieDeliveryOptions = roadieDeliveryOptions;
    }

    @JsonProperty("reference_id")
    private final String referenceId;

    @JsonProperty("idempotency_key")
    private final String idempotencyKey;

    @JsonProperty("alternate_id_1")
    private final String alternateId1;

    @JsonProperty("alternate_id_2")
    private final String alternateId2;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("items")
    private final ArrayList<RoadieItem> roadieItemList;

    @JsonProperty("pickup_location")
    private final RoadieLocation pickupLocation;

    @JsonProperty("delivery_location")
    private final RoadieLocation deliveryLocation;

    @JsonProperty("pickup_after")
    private final  String pickupAfter;

    @JsonProperty("deliver_between")
    private final  RoadieTimeWindow roadieTimeWindow;

    @JsonProperty("options")
    private final RoadieDeliveryOptions roadieDeliveryOptions;

    private Date timeStamp;

    public String getReferenceId() {
        return referenceId;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getAlternateId1() {
        return alternateId1;
    }

    public String getAlternateId2() {
        return alternateId2;
    }

    public String getDescription() {
        return description;
    }

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

    public RoadieDeliveryOptions getRoadieDeliveryOptions() {
        return roadieDeliveryOptions;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
