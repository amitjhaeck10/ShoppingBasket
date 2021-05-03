package com.domain.Offer;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Product;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DiscountPercentageApply extends AbstractApply {

    @Override
    public double filterPrice(List<Product> items,Object discount) throws ItemNotSameTypeException {
        super.filterItemsBeforeCalculatePrice(items);
        if (items.size() == 0) {
            return 0;
        }
        Integer percentage = (Integer) discount;
        double price = items.stream().findFirst().get().getPrice();

        return items.size() * (price-(price*percentage/100));
    }
}
