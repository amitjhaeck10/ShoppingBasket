package com.domain.Offer;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Product;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BuyTwoShirtHalfPriceTieApply extends AbstractApply {

    @Override
    public double filterPrice(List<Product> items,Object obj) throws ItemNotSameTypeException {
        super.filterItemsBeforeCalculatePrice(items);

        if (items.size() == 0) {
            return 0;
        }

        Integer size = (Integer)obj;
        AtomicInteger numberGroup = new AtomicInteger(size/2);
       
        int remianingTie =0;
        if(items.size()<numberGroup.get()) {
        	return 0;
        }else {
        	remianingTie = items.size() - numberGroup.get();
        }
        
        return remianingTie * items.get(0).getPrice() + ((numberGroup.get() * items.get(0).getPrice())/2);
    }
}
