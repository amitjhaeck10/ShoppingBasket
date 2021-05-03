package com.domain.filter;

import com.domain.Offer.BuyTwoShirtHalfPriceTieApply;
import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Product;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class BuyTwoShirtHalfPriceTieTest {

    private BuyTwoShirtHalfPriceTieApply buyTwoShirtHalfPriceTieApply;

    @Test
    public void testBuyTwoShirtForHalfPriceOfTie() throws ItemNotSameTypeException {
        buyTwoShirtHalfPriceTieApply = new BuyTwoShirtHalfPriceTieApply();

        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();
        threadSafeItemList.add(new Product("Shirt", 12.5));
        threadSafeItemList.add(new Product("Shirt", 12.5));
        threadSafeItemList.add(new Product("Tie", 9.5));

        assertEquals(buyTwoShirtHalfPriceTieApply.filterPrice(threadSafeItemList,2), 4.5d, 4.5);
    }

    @Test
    public void testBuyTwoShirtHalfPriceTieWithEmptyList() throws ItemNotSameTypeException {
        buyTwoShirtHalfPriceTieApply = new BuyTwoShirtHalfPriceTieApply();
        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();

        assertEquals(buyTwoShirtHalfPriceTieApply.filterPrice(threadSafeItemList,2), 0.0d, 0.01);
    }

    @Test
    public void testBuyTwoShirtHalfPriceTie() throws ItemNotSameTypeException {
        buyTwoShirtHalfPriceTieApply = new BuyTwoShirtHalfPriceTieApply();
        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();
        threadSafeItemList.add(new Product("Tie", 9.5));
        threadSafeItemList.add(new Product("Tie", 9.5));

        assertEquals(buyTwoShirtHalfPriceTieApply.filterPrice(threadSafeItemList,0), 19.0d, 0.19);
    }

    @Test
    public void testBuyTwoShirtHalfPriceTieWithSingleItem() throws ItemNotSameTypeException {
        buyTwoShirtHalfPriceTieApply = new BuyTwoShirtHalfPriceTieApply();
        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();
        threadSafeItemList.add(new Product("Tie", 50));

        assertEquals(buyTwoShirtHalfPriceTieApply.filterPrice(threadSafeItemList,0), 50.0d, 0.50);
    }

    @Test
    public void testBuyTwoShirtHalfPriceTieWithEvenList() throws ItemNotSameTypeException {
        buyTwoShirtHalfPriceTieApply = new BuyTwoShirtHalfPriceTieApply();
        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();
        threadSafeItemList.add(new Product("Tie", 9.50));
        threadSafeItemList.add(new Product("Tie", 9.50));
        threadSafeItemList.add(new Product("Tie", 9.50));
        threadSafeItemList.add(new Product("Tie", 9.50));

        assertEquals(buyTwoShirtHalfPriceTieApply.filterPrice(threadSafeItemList,4), 28.5d, 2.85);
    }

    @Test(expected = ItemNotSameTypeException.class)
    public void testBuyThreeForPriceOfTwoWithException() throws ItemNotSameTypeException {
        buyTwoShirtHalfPriceTieApply = new BuyTwoShirtHalfPriceTieApply();
        CopyOnWriteArrayList<Product> threadSafeItemList = new CopyOnWriteArrayList<Product>();
        threadSafeItemList.add(new Product("Tie", 9.5));
        threadSafeItemList.add(new Product("Tie", 9.6));
        buyTwoShirtHalfPriceTieApply.filterPrice(threadSafeItemList,0);
    }

}