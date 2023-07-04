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
    private Date purchaseDate;
    private List<Item> items;
    private Double total;

    @JsonCreator
    public Receipt(String retailer, String purchaseDate, String purchaseTime, List<Item> items, Double total) throws ParseException {
        this.retailer = retailer;
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.purchaseDate = parser.parse(purchaseDate + " " + purchaseTime);
        this.items = items;
        this.total = total;
    }

    public String getRetailer() {
        return retailer;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public List<Item> getItems() {
        return items;
    }

    public Double getTotal() {
        return total;
    }

}
