package com.vt.fish.model.roadierequest;

public class RoadieTimeWindow {

    public RoadieTimeWindow(String start, String end) {
        this.start = start;
        this.end = end;
    }

    private final String start;
    private final String end;

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
