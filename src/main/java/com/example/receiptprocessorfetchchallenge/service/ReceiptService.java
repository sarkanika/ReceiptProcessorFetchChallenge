package com.example.receiptprocessorfetchchallenge.service;

import com.example.receiptprocessorfetchchallenge.model.Item;
import com.example.receiptprocessorfetchchallenge.model.Receipt;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReceiptService {

    public ReceiptService() {
    }

    /**
     * Method to calculate the points based on the rules
     * mentioned in the problem statement
     * @param receipt
     * @return total points
     */
    public Double getPoints(Receipt receipt) {
        List<Item> itemList = receipt.getItems();
        Double points = 0.00;
        Double total = receipt.getTotal();
        Date date = receipt.getPurchaseDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd"); //using SimpleDateFormat to get
        // individual date and hour numbers
        int day = Integer.parseInt(simpleDateFormat.format(date));
        simpleDateFormat.applyPattern("HH");
        int hour = Integer.parseInt(simpleDateFormat.format(date));
        points += receipt.getRetailer().chars().filter(Character::isLetterOrDigit).count(); //One point for every alphanumeric character
        // in the retailer name
        if(hour >= 14 && hour<= 16) { //10 points if the time of purchase is after 2:00pm and before 4:00pm
            points += 10.00;
        }
        if(day % 2 == 1) { //6 points if the day in the purchase date is odd
            points += 6.00;
        }
        if(isWholeNumber(total/0.25)) { //25 points if the total is a multiple of 0.25
            points += 25.00;
        }
        if(isWholeNumber(total)) { //50 points if the total is a round dollar amount with no cents
            points += 50.00;
        }
        for (int i = 0; i < itemList.size(); i++) {
            String sd = itemList.get(i).getShortDescription();
            Double price = itemList.get(i).getPrice();
            if(i % 2 == 1) { //5 points for every two items on the receipt
                points += 5.00;
            }
            if(sd.length() % 3 == 0) { /*If the trimmed length of the item description is a multiple of 3,
                                        multiply the price by 0.2 and round up to the nearest integer.
                                        The result is the number of points earned.
                                        The short description has already been trimmed earlier*/
                points += Math.ceil(price * 0.20);
            }
        }
        return points;
    }

    /**
     * Check if a given double is a whole number or not
     * @param d
     * @return true if the given number is a whole number
     */
    private boolean isWholeNumber(Double d) {
        return d == Math.floor(d);
    }
}
