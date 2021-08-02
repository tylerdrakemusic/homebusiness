package com.vt.fish.model.roadieresponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vt.fish.model.roadierequest.RoadieDeliveryOptions;
import com.vt.fish.model.roadierequest.RoadieItem;
import com.vt.fish.model.roadierequest.RoadieLocation;
import com.vt.fish.model.roadierequest.RoadieTimeWindow;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document(collection = "collection")
public class ShipmentResponse {
    private String correlationId;
    @JsonProperty("reference_id")
    private String referenceId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("state")
    private String state;
    @JsonProperty("alternate_id_1")
    private String alternateId1;
    @JsonProperty("alternate_id_2")
    private String alternateId2;
    @JsonProperty("items")
    private ArrayList<RoadieItem> roadieItemList;
    @JsonProperty("pickup_location")
    private RoadieLocation pickupLocation;
    @JsonProperty("delivery_location")
    private  RoadieLocation deliveryLocation;
    @JsonProperty("pickup_after")
    private  String pickupAfter;
    @JsonProperty("deliver_between")
    private RoadieTimeWindow roadieTimeWindow;
    @JsonProperty("options")
    private RoadieDeliveryOptions roadieDeliveryOptions;
    @JsonProperty("tracking_number")
    private String trackingNumber;
    @JsonProperty("price")
    private double price;
    @JsonProperty("estimated_distance")
    private double estimatedDistance;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;

    private String vibrantTropicalRequestId;

    @JsonIgnore
    private Date timeStamp;

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAlternateId1() {
        return alternateId1;
    }

    public void setAlternateId1(String alternateId1) {
        this.alternateId1 = alternateId1;
    }

    public String getAlternateId2() {
        return alternateId2;
    }

    public void setAlternateId2(String alternateId2) {
        this.alternateId2 = alternateId2;
    }

    public ArrayList<RoadieItem> getRoadieItemList() {
        return roadieItemList;
    }

    public void setRoadieItemList(ArrayList<RoadieItem> roadieItemList) {
        this.roadieItemList = roadieItemList;
    }

    public RoadieLocation getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(RoadieLocation pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public RoadieLocation getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(RoadieLocation deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getPickupAfter() {
        return pickupAfter;
    }

    public void setPickupAfter(String pickupAfter) {
        this.pickupAfter = pickupAfter;
    }

    public RoadieTimeWindow getRoadieTimeWindow() {
        return roadieTimeWindow;
    }

    public void setRoadieTimeWindow(RoadieTimeWindow roadieTimeWindow) {
        this.roadieTimeWindow = roadieTimeWindow;
    }

    public RoadieDeliveryOptions getRoadieDeliveryOptions() {
        return roadieDeliveryOptions;
    }

    public void setRoadieDeliveryOptions(RoadieDeliveryOptions roadieDeliveryOptions) {
        this.roadieDeliveryOptions = roadieDeliveryOptions;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getEstimatedDistance() {
        return estimatedDistance;
    }

    public void setEstimatedDistance(double estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getVibrantTropicalRequestId() {
        return vibrantTropicalRequestId;
    }

    public void setVibrantTropicalRequestId(String vibrantTropicalRequestId) {
        this.vibrantTropicalRequestId = vibrantTropicalRequestId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
