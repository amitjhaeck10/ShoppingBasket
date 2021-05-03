package com.domain.Offer;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Product;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractApply {

    public abstract double filterPrice(List<Product> items,Object obj) throws ItemNotSameTypeException;

    public void filterItemsBeforeCalculatePrice(List<Product> items) throws ItemNotSameTypeException {
        HashSet<String> itemNames = items.stream()
                .map(Product::getName)
                .collect(Collectors.toCollection(HashSet::new));

        if (itemNames.size() > 1) {
            throw new ItemNotSameTypeException("Items are not same type");
        }
    }
}
