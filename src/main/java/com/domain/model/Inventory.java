package com.domain.model;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.IntStream;

import com.domain.Offer.*;
import com.domain.utility.PromotionType;

public class Inventory {

    private ConcurrentMap<String, Product> listingItems = new ConcurrentHashMap<String, Product>();
    private ConcurrentMap<String, PromotionType> promotions = new ConcurrentHashMap<String, PromotionType>();
    private ConcurrentMap<String, AbstractApply> apply = new ConcurrentHashMap<String, AbstractApply>();
    private MarkedPriceApply markedPriceApply;
    private DiscountPercentageApply discountPercentageApply;
    private BuyTwoShirtHalfPriceTieApply buyTwoShirtHalfPriceTieApply;

    public ConcurrentMap<String, Product> getListingItems() {
        return listingItems;
    }

    public ConcurrentMap<String, PromotionType> getPromotions() {
        return promotions;
    }

    public ConcurrentMap<String, AbstractApply> getApply() {
        return apply;
    }

    public AbstractApply defaultNormalHelper() {
        return new MarkedPriceApply();
    }

    public Inventory(List<Product> items, List<PromotionType> itemPromotions) {

        markedPriceApply = new MarkedPriceApply();
        discountPercentageApply = new DiscountPercentageApply();
        buyTwoShirtHalfPriceTieApply = new BuyTwoShirtHalfPriceTieApply();

        IntStream.range(0, items.size()).forEach(i -> {
            Product item = items.get(i);

            listingItems.put(item.getName(), item);
            promotions.put(item.getName(), itemPromotions.get(i));

            if (itemPromotions.get(i).equals(PromotionType.DISCOUNT_PERCENTAGE)) {
                apply.put(item.getName(), discountPercentageApply);
            } else if (itemPromotions.get(i).equals(PromotionType.BUY_TWO_SHIRT_HALF_PRICE_OF_TIE)) {
                apply.put(item.getName(), buyTwoShirtHalfPriceTieApply);
            } else {
                apply.put(item.getName(), markedPriceApply);
            }
        });
    }
}