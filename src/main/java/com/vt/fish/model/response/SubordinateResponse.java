package com.vt.fish.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "collection")
public class SubordinateResponse {

    private String correlationId;
    private String message;
    private Date timeStamp;
    private String vibrantTropicalRequestId;

    public String getCorrelationId() {
        return correlationId;
    }

    public String getMessage() {
        return message;
    }
    public Date getTimeStamp() {
        return timeStamp;
    }
    public String getVibrantTropicalRequestId() {
        return vibrantTropicalRequestId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setVibrantTropicalRequestId(String vibrantTropicalRequestId) {
        this.vibrantTropicalRequestId = vibrantTropicalRequestId;
    }
}
