package com.vt.fish.model.roadieresponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class RoadieErrorResponse {
    @JsonProperty("errors")
    private ArrayList<RoadieError> roadieErrorArrayList;

    public ArrayList<RoadieError> getRoadieErrorArrayList() {
        return roadieErrorArrayList;
    }

    @Override
    public String toString() {
        return "RoadieErrorResponse{" +
                "roadieErrorArrayList=" + roadieErrorArrayList +
                '}';
    }
}
