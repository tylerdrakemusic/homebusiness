package com.vt.fish.model.product;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
    private String name;
    private int count;
    private int perBag;
    private double bagLength;
    private double bagWidth;
    private double bagHeight;
    private double bagWeight;
    private double price;
    private String imageLocation;
    private String movieLocation;

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public int getPerBag() {
        return perBag;
    }

    public double getBagLength() {
        return bagLength;
    }

    public double getBagWidth() {
        return bagWidth;
    }

    public double getBagHeight() {
        return bagHeight;
    }

    public double getBagWeight() {
        return bagWeight;
    }

    public double getPrice() {
        return price;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public String getMovieLocation() {
        return movieLocation;
    }
}
