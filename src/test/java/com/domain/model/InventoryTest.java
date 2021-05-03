package com.domain.model;

import org.junit.Test;

import com.domain.Offer.DiscountPercentageApply;
import com.domain.Offer.BuyTwoShirtHalfPriceTieApply;
import com.domain.Offer.MarkedPriceApply;
import com.domain.utility.PromotionType;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class InventoryTest {

    private CopyOnWriteArrayList<Product> items = new CopyOnWriteArrayList<Product>();
    private CopyOnWriteArrayList<PromotionType> itemPromotions = new CopyOnWriteArrayList<PromotionType>();

    @Test
    public void testCreateInventory() {
        items.add(new Product("Jacket", 49.9));
        items.add(new Product("Trousers", 35.5));
        items.add(new Product("Shirt", 12.5));
        items.add(new Product("Tie", 9.5));

        itemPromotions.add(PromotionType.MARKED_PRICE);
        itemPromotions.add(PromotionType.DISCOUNT_PERCENTAGE);
        itemPromotions.add(PromotionType.MARKED_PRICE);
        itemPromotions.add(PromotionType.BUY_TWO_SHIRT_HALF_PRICE_OF_TIE);

        Inventory inventory = new Inventory(items, itemPromotions);

        assertNotNull(inventory);

        assertNotNull(inventory.getApply());
        
        assertTrue(inventory.getApply().get("Jacket") instanceof MarkedPriceApply);
        assertTrue(inventory.getApply().get("Shirt") instanceof MarkedPriceApply);
        assertTrue(inventory.getApply().get("Trousers") instanceof DiscountPercentageApply);
        assertTrue(inventory.getApply().get("Tie") instanceof BuyTwoShirtHalfPriceTieApply);

        assertNotNull(inventory.getListingItems());
        assertEquals(inventory.getListingItems().get("Jacket").getName(), "Jacket");
        assertEquals(inventory.getListingItems().get("Jacket").getPrice(), 49.9d, 0.01);
        assertEquals(inventory.getListingItems().get("Trousers").getName(), "Trousers");
        assertEquals(inventory.getListingItems().get("Trousers").getPrice(), 35.5, 0.01);
        assertEquals(inventory.getListingItems().get("Shirt").getName(), "Shirt");
        assertEquals(inventory.getListingItems().get("Shirt").getPrice(), 12.5d, 0.01);
        assertEquals(inventory.getListingItems().get("Tie").getName(), "Tie");
        assertEquals(inventory.getListingItems().get("Tie").getPrice(), 9.5d, 0.01);

        assertNotNull(inventory.getPromotions());
        assertEquals(inventory.getPromotions().get("Jacket"), PromotionType.MARKED_PRICE);
        assertEquals(inventory.getPromotions().get("Shirt"), PromotionType.MARKED_PRICE);
        assertEquals(inventory.getPromotions().get("Trousers"), PromotionType.DISCOUNT_PERCENTAGE);
        assertEquals(inventory.getPromotions().get("Tie"), PromotionType.BUY_TWO_SHIRT_HALF_PRICE_OF_TIE);
    }
}
