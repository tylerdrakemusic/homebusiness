package com.vt.fish.model.roadierequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoadieAddress {

    public RoadieAddress(){
    }

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

    private String name;
    @JsonProperty("store_number")
    private String storeNumber;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zip;
    private Double latitude;
    private Double longitude;

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
