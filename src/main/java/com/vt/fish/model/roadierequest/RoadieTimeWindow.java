package com.vt.fish.model.roadierequest;

public class RoadieTimeWindow {

    public RoadieTimeWindow(){
    }

    public RoadieTimeWindow(String start, String end) {
        this.start = start;
        this.end = end;
    }

    private String start;
    private String end;

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
