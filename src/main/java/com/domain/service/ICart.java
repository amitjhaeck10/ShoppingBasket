package com.domain.service;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Product;

import java.util.List;

public interface ICart {
    public List<Product> getItems();

    public void empty();

    public void add(String itemName) throws ItemNotSameTypeException;

    public void add(List<String> itemNames) throws ItemNotSameTypeException;

    public double calculateFinalPrice(String name, List<Product> productList, Integer discount) throws ItemNotSameTypeException;

    public double calculateMarkerPrice(List<Product> productList) throws ItemNotSameTypeException;
    
    public void printInvoice() throws ItemNotSameTypeException;
}
