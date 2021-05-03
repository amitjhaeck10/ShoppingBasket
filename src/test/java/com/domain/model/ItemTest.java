package com.domain.model;

import com.domain.exception.ItemNotSameTypeException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {

    private static final double DELTA = 1e-15;

    @Test
    public void createItem() {
        Product item = new Product("Jacket", 49.9);
        assertEquals(item.getName(), "Jacket");
        assertEquals(item.getPrice(), 49.9, 0.01);
    }

    @Test
    public void addItem() throws ItemNotSameTypeException {

        Product item = new Product("Shirt", 12.5);
        Product newItem = new Product(item);
        newItem.setName("Shirt");
        newItem.setPrice(12.5);
        assertEquals(newItem.getName(), "Shirt");
        assertEquals(newItem.getPrice(), 12.5, DELTA);

    }
}