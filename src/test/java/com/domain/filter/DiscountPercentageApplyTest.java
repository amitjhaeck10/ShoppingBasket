package com.domain.filter;

import com.domain.Offer.DiscountPercentageApply;
import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Product;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class DiscountPercentageApplyTest {

    private DiscountPercentageApply discountPercentageApply;

    @Test
    public void testDiscount10pcent() throws ItemNotSameTypeException {
        discountPercentageApply = new DiscountPercentageApply();

        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();
        threadSafeItemList.add(new Product("Trousers", 49.5));
        threadSafeItemList.add(new Product("Trousers", 49.5));

        assertEquals(discountPercentageApply.filterPrice(threadSafeItemList,10), 89.1, 89.1);
    }

    @Test
    public void testDiscountZeroPercent() throws ItemNotSameTypeException {
        discountPercentageApply = new DiscountPercentageApply();
        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();

        assertEquals(discountPercentageApply.filterPrice(threadSafeItemList,0), 0.0d, 0.00);
    }

    @Test
    public void testDiscount10Percent() throws ItemNotSameTypeException {
        discountPercentageApply = new DiscountPercentageApply();
        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();
        threadSafeItemList.add(new Product("Toursers", 1));

        assertEquals(discountPercentageApply.filterPrice(threadSafeItemList,10), 0.9, 0.9);
    }

    @Test(expected = ItemNotSameTypeException.class)
    public void testDiscountPercentageWithException() throws ItemNotSameTypeException {
        discountPercentageApply = new DiscountPercentageApply();
        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();
        threadSafeItemList.add(new Product("Trousers", 15));
        threadSafeItemList.add(new Product("Tie", 0.6));
        discountPercentageApply.filterPrice(threadSafeItemList,30);
    }

}