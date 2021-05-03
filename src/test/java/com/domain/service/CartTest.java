package com.domain.service;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Inventory;
import com.domain.model.Product;
import com.domain.utility.PromotionType;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class CartTest {

    private Inventory inventory;
    private List<Product> items = new CopyOnWriteArrayList<Product>();
    private List<PromotionType> itemPromotions = new CopyOnWriteArrayList<PromotionType>();
    Map<String,List<Product>> prodMap;

    @Before
    public void setUp() {
        items.add(new Product("Jacket", 49.9));
        items.add(new Product("Trousers", 35.5));
        items.add(new Product("Shirt", 12.5));
        items.add(new Product("Tie", 9.5));

        itemPromotions.add(PromotionType.MARKED_PRICE);
        itemPromotions.add(PromotionType.DISCOUNT_PERCENTAGE);
        itemPromotions.add(PromotionType.MARKED_PRICE);
        itemPromotions.add(PromotionType.BUY_TWO_SHIRT_HALF_PRICE_OF_TIE);

        inventory = new Inventory(items, itemPromotions);
        prodMap = items.stream().collect(Collectors.groupingBy(Product::getName));
    }

    @Test
    public void testCreateBasket() throws ItemNotSameTypeException {
        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("Jacket", "Trousers", "Trousers", "Shirt", "Tie", "Trousers", "Shirt", "Shirt", "Tie", "Tie"));

        Cart cart = new Cart(inventory);
        cart.add(order);

        assertNotNull(cart);
        assertTrue(cart.getItems().size() > 0);

    }

    @Test
    public void testCalculateFinalPrice() throws ItemNotSameTypeException {
    	 List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("Jacket", "Trousers", "Trousers", "Shirt", "Tie", "Trousers", "Shirt", "Shirt", "Tie", "Tie"));

        Cart cart = new Cart(inventory);
        cart.add(order);

        assertEquals(cart.calculateFinalPrice("Shirt",prodMap.get("Shirt"),0), 12.5d, 0.001);
    }

    @Test
    public void testCalculateMarkedPrice() throws ItemNotSameTypeException {
    	 List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("Jacket", "Trousers", "Trousers", "Shirt", "Tie", "Trousers", "Shirt", "Shirt", "Tie", "Tie"));

        Cart cart = new Cart(inventory);
        cart.add(order);

        assertEquals(cart.calculateMarkerPrice(prodMap.get("Tie")), 9.5d, 0.001);
    }

    @Test
    public void testEmptyCart() throws ItemNotSameTypeException {
    	 List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("Jacket", "Trousers", "Trousers", "Shirt", "Tie"));

        Cart cart = new Cart(inventory);
        cart.add(order);
        cart.empty();
        
        

        assertEquals(cart.calculateMarkerPrice(prodMap.get("Jacket")), 49.9, 0.0);
        assertEquals(cart.calculateFinalPrice("Jacket",prodMap.get("Jacket"),0), 49.9, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatchEmptyAddException() throws ItemNotSameTypeException {
        Cart cart = new Cart(inventory);
        cart.add("");
        cart.empty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatchEmptyListOnAddException() throws ItemNotSameTypeException {
        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("", ""));
        Cart cart = new Cart(inventory);
        cart.add(order);
        cart.empty();
    }

    @Test
    public void testConcurrency() throws InterruptedException, ItemNotSameTypeException {

        Inventory inventory = new Inventory(items, itemPromotions);
        Cart cart = new Cart(inventory);

        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("Jacket", "Trousers", "Trousers", "Shirt", "Tie", "Trousers", "Shirt", "Shirt", "Tie", "Tie"));

        AtomicInteger finished = new AtomicInteger();

        for (int i = 0; i < 100; i++) {

            cart.add(getRandomItem());

            new Thread(() ->
            {
                cart.add(order);
                finished.incrementAndGet();

            }).start();
        }

        while (finished.get() < 100) {
            Thread.sleep(100);
        }

        assertTrue(cart.calculateMarkerPrice(prodMap.get("Jacket")) > 0);
        assertTrue(cart.calculateFinalPrice("Jacket",prodMap.get("Jacket"),null) > 0);
        assertEquals(cart.getItems().size(), 1100);
        cart.empty();
    }


    private synchronized String getRandomItem() {
    	 List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("Jacket", "Trousers", "Trousers", "Shirt", "Tie", "Trousers", "Shirt", "Shirt", "Tie", "Tie"));
        int rnd = new Random().nextInt(order.size());
        return order.get(rnd);
    }
}