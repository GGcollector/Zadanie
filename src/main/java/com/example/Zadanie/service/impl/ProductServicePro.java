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
public class ProductServicePro implements ProductService {
    @Value("${locale}")
    private String locale;
    private double summaryPrice;
    @Value("${vatRate}")
    private double vatRate;
    @Value("${discount}")
    private double discount;
    private double summaryVatPrice;
    private double summaryVatPriceDiscountIncluded;
    private final CreateProducts createProducts;
    private final MessageSource messageSource;
    public ProductServicePro(CreateProducts createProducts, MessageSource messageSource) {
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
        summaryVatPrice = summaryPrice * vatRate;
        summaryVatPriceDiscountIncluded = summaryVatPrice - discount;
        System.out.println(messageSource.getMessage("Price_of_all_products", new Object[]{summaryPrice}, Locale.ENGLISH));
        System.out.println(messageSource.getMessage("Price_of_all_products_plus_VAT", new Object[]{summaryVatPrice}, Locale.ENGLISH));
        System.out.println(messageSource.getMessage("Price_of_all_products_plus_VAT_minus_discount", new Object[]{summaryVatPriceDiscountIncluded}, Locale.ENGLISH));
    }
}
