package com.example.receiptprocessorfetchchallenge.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Class to represent the json object item
 * @author Kanika Saraswat
 */
public class Item {

    private String shortDescription;
    private Double price;

    @JsonCreator
    public Item(String shortDescription, String price) {
        this.shortDescription = shortDescription.trim();
        this.price = Double.valueOf(price);
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public Double getPrice() {
        return price;
    }
}
