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
@Profile("Plus")
public class ProductServicePlus extends ProductServiceStart{
    @Value("${locale}")
    private String locale;
    @Value("${vatRate}")
    private double vatRate;
    private double summaryVatPrice;

    public double getSummaryVatPrice() {
        return summaryVatPrice;
    }

    public ProductServicePlus(CreateProducts createProducts, MessageSource messageSource) {
        super(createProducts,messageSource);
    }
    @Override
    public void displayInfo() {
        super.displayInfo();

        summaryVatPrice =  getSummaryPrice() * vatRate;

        System.out.println(messageSource.getMessage("Price_of_all_products_plus_VAT", new Object[]{summaryVatPrice}, Locale.ENGLISH));
    }
}
