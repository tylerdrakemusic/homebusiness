package com.vt.fish.model.roadieresponse;

public class RoadieError {
    private String code;
    private String parameter;
    private String message;

    public String getCode() {
        return code;
    }

    public String getParameter() {
        return parameter;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "RoadieError{" +
                "code='" + code + '\'' +
                ", parameter='" + parameter + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
