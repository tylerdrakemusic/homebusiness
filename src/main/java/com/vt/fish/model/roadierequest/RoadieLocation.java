package com.vt.fish.model.roadierequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoadieLocation {

    public RoadieLocation(RoadieAddress roadieAddress, RoadieContact roadieContact) {
        this.roadieAddress = roadieAddress;
        this.roadieContact = roadieContact;
    }

    @JsonProperty("address")
    private final RoadieAddress roadieAddress;
    @JsonProperty("contact")
    private final RoadieContact roadieContact;

    public RoadieAddress getRoadieAddress() {
        return roadieAddress;
    }

    public RoadieContact getRoadieContact() {
        return roadieContact;
    }
}
