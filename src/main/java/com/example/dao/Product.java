package com.example.dao;

import lombok.Data;

@Data
public class Product {

    private String product_name;

    public Product(String product_name) {
        this.product_name = product_name;
    }
}
