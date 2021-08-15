package com.vt.fish.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "roadie")
public class RoadieRequestServiceConfig {
    private String productDescription;
    private String pickupStreet;
    private String pickupStreet2;
    private String pickupCity;
    private String pickupState;
    private String pickupZip;
    private String pickupName;
    private String pickupPhoneNumber;

    private String authorizationType;
    private String url;
    private String key;

    private String estimatePath;
    private String shipmentPath;

    public String getProductDescription() {
        return productDescription;
    }

    public String getPickupStreet() {
        return pickupStreet;
    }

    public String getPickupStreet2(){
        return pickupStreet2;
    }

    public String getPickupCity() {
        return pickupCity;
    }

    public String getPickupState() {
        return pickupState;
    }

    public String getPickupZip() {
        return pickupZip;
    }

    public String getPickupName() {
        return pickupName;
    }

    public String getPickupPhoneNumber() {
        return pickupPhoneNumber;
    }

    public String getAuthorizationType() {
        return authorizationType;
    }

    public String getUrl() {
        return url;
    }

    public String getKey() {
        return key;
    }

    public String getEstimatePath() {
        return estimatePath;
    }

    public String getShipmentPath() {
        return shipmentPath;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setPickupStreet(String pickupStreet) {
        this.pickupStreet = pickupStreet;
    }

    public void setPickupStreet2(String pickupStreet2) {
        this.pickupStreet2 = pickupStreet2;
    }

    public void setPickupCity(String pickupCity) {
        this.pickupCity = pickupCity;
    }

    public void setPickupState(String pickupState) {
        this.pickupState = pickupState;
    }

    public void setPickupZip(String pickupZip) {
        this.pickupZip = pickupZip;
    }

    public void setPickupName(String pickupName) {
        this.pickupName = pickupName;
    }

    public void setPickupPhoneNumber(String pickupPhoneNumber) {
        this.pickupPhoneNumber = pickupPhoneNumber;
    }

    public void setAuthorizationType(String authorizationType) {
        this.authorizationType = authorizationType;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setEstimatePath(String estimatePath) {
        this.estimatePath = estimatePath;
    }

    public void setShipmentPath(String shipmentPath) {
        this.shipmentPath = shipmentPath;
    }

}
