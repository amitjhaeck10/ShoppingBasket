package com.domain.filter;

import com.domain.Offer.DiscountPercentageApply;
import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Product;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class DiscountPercentageApplyTest {

    private DiscountPercentageApply buyOneGetOneFilter;

    @Test
    public void testDiscount10pcent() throws ItemNotSameTypeException {
        buyOneGetOneFilter = new DiscountPercentageApply();

        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();
        threadSafeItemList.add(new Product("Trousers", 49.9));
        threadSafeItemList.add(new Product("Trousers", 50.0));

        assertEquals(buyOneGetOneFilter.filterPrice(threadSafeItemList,10), 1.0d, 0.01);
    }

    @Test
    public void testDiscountZeroPercent() throws ItemNotSameTypeException {
        buyOneGetOneFilter = new DiscountPercentageApply();
        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();

        assertEquals(buyOneGetOneFilter.filterPrice(threadSafeItemList,0), 0.0d, 0.00);
    }

    @Test
    public void testDiscount10Percent() throws ItemNotSameTypeException {
        buyOneGetOneFilter = new DiscountPercentageApply();
        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();
        threadSafeItemList.add(new Product("Toursers", 1));

        assertEquals(buyOneGetOneFilter.filterPrice(threadSafeItemList,10), 1.0d, 0.01);
    }

    @Test(expected = ItemNotSameTypeException.class)
    public void testDiscountPercentageWithException() throws ItemNotSameTypeException {
        buyOneGetOneFilter = new DiscountPercentageApply();
        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();
        threadSafeItemList.add(new Product("Trousers", 15));
        threadSafeItemList.add(new Product("Tie", 0.6));
        buyOneGetOneFilter.filterPrice(threadSafeItemList,30);
    }

}