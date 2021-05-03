package com.domain.model;

import com.domain.exception.ItemNotSameTypeException;

public class Product {

    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(Product item) throws ItemNotSameTypeException {

        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }

        this.name = item.name;
        this.price = item.getPrice();
    }
}
