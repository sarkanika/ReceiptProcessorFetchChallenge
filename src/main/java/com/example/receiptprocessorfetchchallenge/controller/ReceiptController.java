package com.example.receiptprocessorfetchchallenge.controller;

import com.example.receiptprocessorfetchchallenge.model.Item;
import com.example.receiptprocessorfetchchallenge.model.Receipt;
import com.example.receiptprocessorfetchchallenge.service.ReceiptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Rest controller class to POST and GET json messages
 */
@RestController
@RequestMapping(value = "/receipts")
public class ReceiptController {

    private Map<String, Receipt> receiptRepo = new HashMap<>(); //to store the uuid and the receipt object
    private ReceiptService receiptService = new ReceiptService();

    /**
     * Method to get receipt as a json object and convert it into a POJO
     * @param receipt
     * @return json object with the uuid of the receipt in the OK response
     */
    @PostMapping(value = "/process", produces = "application/json")
    public ResponseEntity<Map<String, Object>> getReceipt(@RequestBody Receipt receipt) {
        String uuid = UUID.randomUUID().toString();
        receiptRepo.put(uuid, receipt);
        return ResponseEntity.ok().body(Map.of("id", uuid));
    }

    /**
     * Method to receive uuid in the uri and retrieve the receipt from the uuid
     * @param uuid
     * @return json object containing the points in the ok response
     */
    @PostMapping(value = "{id}/points", produces = "application/json")
    public ResponseEntity<Map<String, Object>> getPoints(@PathVariable ("id") String uuid) {
        Receipt receipt = receiptRepo.get(uuid);
        Double points = receiptService.getPoints(receipt);
        return ResponseEntity.ok().body(Map.of("points", points));
    }


}
