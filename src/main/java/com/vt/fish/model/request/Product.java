package com.vt.fish.model.request;

public class Product {
    private double dollars;
    private String productName;
    private int quantity;
    private String subproduct;

    public Product() {
    }

    public Product(double dollars, String productName, int quantity, String subproduct) {
        this.dollars = dollars;
        this.productName = productName;
        this.quantity = quantity;
        this.subproduct = subproduct;
    }

    public double getDollars() {
        return dollars;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSubproduct() { return subproduct; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
