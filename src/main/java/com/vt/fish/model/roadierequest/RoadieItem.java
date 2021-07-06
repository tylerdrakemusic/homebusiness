package com.vt.fish.model.roadierequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoadieItem {
    
    public RoadieItem(String description, String referenceId, double value, double length, double width, double height, double weight, int quantity) {
        this.description = description;
        this.referenceId = referenceId;
        this.value = value;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.quantity = quantity;
    }
    
    private final String description;
    @JsonProperty("reference_id")
    private final String referenceId;
    private final double value;
    private final double length;
    private final double width;
    private final double height;
    private final double weight;
    private final int quantity;

    public String getDescription() {
        return description;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public double getValue() {
        return value;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getQuantity() {
        return quantity;
    }
}
