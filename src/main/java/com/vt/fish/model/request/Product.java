package com.vt.fish.model.request;

public class Product {
    private double dollars;
    private String productName;
    private int quantity;

    public Product() {
    }

    public Product(double dollars, String productName, int quantity) {
        this.dollars = dollars;
        this.productName = productName;
        this.quantity = quantity;
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
}
