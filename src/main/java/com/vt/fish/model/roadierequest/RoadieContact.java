package com.vt.fish.model.roadierequest;

public class RoadieContact {

    public RoadieContact(){
    }

    public RoadieContact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    
    private String name;
    private String phone;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }




}
