package com.domain.model;

import com.domain.exception.ItemNotSameTypeException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {

    private static final double DELTA = 1e-15;

    @Test
    public void createItem() {
        Product item = new Product("Apple", 0.35);
        assertEquals(item.getName(), "Apple");
        assertEquals(item.getPrice(), 0.35, 0.01);
    }

    @Test
    public void addItem() throws ItemNotSameTypeException {

        Product item = new Product("Boo", 0.35);
        Product newItem = new Product(item);
        newItem.setName("Foo");
        newItem.setPrice(0.1);
        assertEquals(newItem.getName(), "Foo");
        assertEquals(newItem.getPrice(), 0.1, DELTA);

    }
}