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
@Profile("Pro")
public class ProductServicePro extends ProductServicePlus {
    @Value("${locale}")
    private String locale;
    @Value("${discount}")
    private double discount;
    private double summaryVatPriceDiscountIncluded;

    public ProductServicePro(CreateProducts createProducts, MessageSource messageSource) {
        super(createProducts, messageSource);
    }
    @Override
    public void displayInfo() {
        super.displayInfo();
        summaryVatPriceDiscountIncluded = getSummaryVatPrice() - discount;
        System.out.println(messageSource.getMessage("Price_of_all_products_plus_VAT_minus_discount", new Object[]{summaryVatPriceDiscountIncluded}, Locale.ENGLISH));
    }
}
