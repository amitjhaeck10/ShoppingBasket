package com.domain.filter;

import com.domain.Offer.MarkedPriceApply;
import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Product;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class MarkedPriceApplyTest {

    private MarkedPriceApply markedPriceFilter;

    @Test
    public void testMarkedPrice() throws ItemNotSameTypeException {
        markedPriceFilter = new MarkedPriceApply();
        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();
        threadSafeItemList.add(new Product("Jacket", 35.50));

        assertEquals(markedPriceFilter.filterPrice(threadSafeItemList,0), 35.5d, 35.50);
    }

    @Test
    public void testMarkedPriceWithEmptyList() throws ItemNotSameTypeException {
        markedPriceFilter = new MarkedPriceApply();
        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();

        assertEquals(markedPriceFilter.filterPrice(threadSafeItemList,0), 0.0d, 0.00);
    }

    @Test
    public void testMarkedPriceWithManyNonePromotedItem() throws ItemNotSameTypeException {
        markedPriceFilter = new MarkedPriceApply();
        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();
        threadSafeItemList.add(new Product("Shirt", 15));
        threadSafeItemList.add(new Product("Shirt", 15));
        threadSafeItemList.add(new Product("Shirt", 15));
        threadSafeItemList.add(new Product("Shirt", 15));

        assertEquals(markedPriceFilter.filterPrice(threadSafeItemList,0), 60.0d, 6.00);
    }

    @Test(expected = ItemNotSameTypeException.class)
    public void testMarkedPriceWithException() throws ItemNotSameTypeException {
        markedPriceFilter = new MarkedPriceApply();
        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();
        threadSafeItemList.add(new Product("Shirt", 15.0));
        threadSafeItemList.add(new Product("Shirt", 15.0));
        markedPriceFilter.filterPrice(threadSafeItemList,0);
    }

}