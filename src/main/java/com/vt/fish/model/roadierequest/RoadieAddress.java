package com.vt.fish.model.roadierequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoadieAddress {

    public RoadieAddress(String name, String storeNumber, String street1, String street2, String city, String state, String zip, Double latitude, Double longitude) {
        this.name = name;
        this.storeNumber = storeNumber;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private final String name;
    @JsonProperty("store_number")
    private final String storeNumber;
    private final String street1;
    private final String street2;
    private final String city;
    private final String state;
    private final String zip;
    private final Double latitude;
    private final Double longitude;

    public String getName() {
        return name;
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    public String getStreet1() {
        return street1;
    }

    public String getStreet2() {
        return street2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
