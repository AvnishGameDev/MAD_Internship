package com.avnishgamedev.ecommerceapp;

import java.io.Serializable;

public class Product implements Serializable {
    public String name;
    public float price;
    public String description;

    public Product() {} // Required by DataSnapshot.getValue(Product.class)

    public Product(String name, float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
