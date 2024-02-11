package com.example.Zadanie.service.impl;

import com.example.Zadanie.model.Product;
import com.example.Zadanie.service.interfaces.ProductService;
import com.example.Zadanie.service.util.CreateProducts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@Profile("Start")
public class ProductServiceStart implements ProductService {
    @Value("${locale}")
    private String locale;

    private double summaryPrice;

    public final CreateProducts createProducts;

    public final MessageSource messageSource;

    public double getSummaryPrice() {
        return summaryPrice;
    }

    public ProductServiceStart(CreateProducts createProducts, MessageSource messageSource) {
        this.createProducts = createProducts;
        this.messageSource = messageSource;
    }
    @Override
    public void displayInfo() {
        List<Product> products = createProducts.create(5);
        products.forEach(product -> summaryPrice += product.getPrice()); // można przerobić aby działało
        for (Product product : products) {
            summaryPrice += product.getPrice();
        }
        summaryPrice = Math.round(summaryPrice); //zaokrągla sume, żeby nie było 3 miejsc po przecinku.
        System.out.println(messageSource.getMessage("Price_of_all_products", new Object[]{summaryPrice}, Locale.ENGLISH));
    }
}
