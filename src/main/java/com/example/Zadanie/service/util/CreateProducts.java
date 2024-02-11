package com.example.Zadanie.service.util;

import com.example.Zadanie.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateProducts {

    private static final double MIN_PRICE = 100;
    private static final double MAX_PRICE = 1000;

//    private int maxValue = Integer.MAX_VALUE;
    private List<Product> productList = new ArrayList<>();



    public List <Product> create(int numberOfProducts) {
        // minimalna ilość produktow - 1
        // maksymalna ilosc produktow = numberOfProducts
        // metoda ma napelniac listę productList
        for (int i=1; i<=numberOfProducts; i++) //{
            productList.add(new Product("produkt_" + i, generatePrice()));
        //}
        return productList;

    }

    private double generatePrice() {
        return Math.random()*(MAX_PRICE-MIN_PRICE) + MIN_PRICE;
    }

}
