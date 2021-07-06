package com.vt.fish.model.roadierequest;

public class RoadieContact {

    public RoadieContact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    
    private final String name;
    private final String phone;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }




}
