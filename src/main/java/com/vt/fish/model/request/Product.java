package com.vt.fish.model.request;

public class Product {
    private double dollars;
    private String productName;
    private int quantity;
    private String subProduct;

    public Product() {
    }

    public Product(double dollars, String productName, int quantity, String subProduct) {
        this.dollars = dollars;
        this.productName = productName;
        this.quantity = quantity;
        this.subProduct = subProduct;
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

    public String getSubProduct() { return subProduct; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
