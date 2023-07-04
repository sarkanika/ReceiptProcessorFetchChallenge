package com.example.receiptprocessorfetchchallenge;

import com.example.receiptprocessorfetchchallenge.model.Item;
import com.example.receiptprocessorfetchchallenge.model.Receipt;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ReceiptControllerTest extends AbstractTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    private List<Item> items;
    private Receipt receipt;
    {
        items = Arrays.asList(
                new Item("Mountain Dew 12PK", "6.49"),
                new Item("Emils Cheese Pizza", "12.25"),
                new Item("Knorr Creamy Chicken", "1.26"),
                new Item("Doritos Nacho Cheese", "3.35"),
                new Item("   Klarbrunn 12-PK 12 FL OZ  ", "12.00")
        );
        try {
            receipt = new Receipt("Target", "2022-01-01", "13:01", items, 35.35);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getReceiptId() throws Exception {
        String uri = "https://localhost:8080/receipts/processing";
        Gson gson = new Gson();
        String json = gson.toJson(receipt);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(json))
                .andReturn();
        int status = result.getResponse().getStatus();
        System.out.println(status);
        //assertEquals(HttpStatus.OK.value(), status);
        String content = result.getResponse().getContentAsString();
        System.out.println(content);
    }
}
