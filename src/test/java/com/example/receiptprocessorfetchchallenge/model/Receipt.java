package com.example.receiptprocessorfetchchallenge.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Class to represent the json object Receipt
 * @author Kanika Saraswat
 */
@JsonRootName("receipt")
public class Receipt {

    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private List<Item> items;
    private Double total;

    @JsonCreator
    public Receipt(String retailer, String purchaseDate, String purchaseTime, List<Item> items, Double total) throws ParseException {
        this.retailer = retailer;
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.items = items;
        this.total = total;
    }

    public String getRetailer() {
        return retailer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public Double getTotal() {
        return total;
    }

}
