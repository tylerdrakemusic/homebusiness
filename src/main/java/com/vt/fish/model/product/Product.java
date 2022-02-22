package com.vt.fish.model.product;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
    public Product(String name, int count, int perBag, double bagLength, double bagWidth, double bagHeight, int bagWeight, double price, String imageLocation, String movieLocation) {
        this.name = name;
        this.count = count;
        this.perBag = perBag;
        this.bagLength = bagLength;
        this.bagWidth = bagWidth;
        this.bagHeight = bagHeight;
        this.bagWeight = bagWeight;
        this.price = price;
        this.imageLocation = imageLocation;
        this.movieLocation = movieLocation;
    }

    private String name;
    private int count;
    private int perBag;
    private double bagLength;
    private double bagWidth;
    private double bagHeight;
    private int bagWeight;
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

    public int getBagWeight() {
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
