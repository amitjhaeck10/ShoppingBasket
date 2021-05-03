package com.domain.Offer;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Product;

import java.util.List;

public class MarkedPriceApply extends AbstractApply {
    @Override
    public double filterPrice(List<Product> items,Object obj) throws ItemNotSameTypeException {
        super.filterItemsBeforeCalculatePrice(items);

        if (items.size() == 0) {
            return 0;
        }

        return items.size() * items.stream().findFirst().get().getPrice();
    }
}
