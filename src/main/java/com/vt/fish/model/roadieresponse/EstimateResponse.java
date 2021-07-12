package com.vt.fish.model.roadieresponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "collection")
public class EstimateResponse {

    private String correlationId;
    private String price;
    private String size;
    private String vibrantTropicalRequestId;

    @JsonIgnore
    private Date timeStamp;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
