package com.domain.utility;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Inventory;
import com.domain.model.Product;
import com.domain.service.Cart;

public class TestShoppingBasket {

	
	public static void main(String[] args) throws ItemNotSameTypeException {
		// This method stub to default data and test quickly
		
		Inventory inventory;
	    List<Product> items = new CopyOnWriteArrayList<Product>();
	    List<PromotionType> itemPromotions = new CopyOnWriteArrayList<PromotionType>();
	    
        items.add(new Product("Jacket", 49.9));
        items.add(new Product("Trousers", 35.5));
        items.add(new Product("Shirt", 12.5));
        items.add(new Product("Tie", 9.5));

        itemPromotions.add(PromotionType.MARKED_PRICE);
        itemPromotions.add(PromotionType.DISCOUNT_PERCENTAGE);
        itemPromotions.add(PromotionType.MARKED_PRICE);
        itemPromotions.add(PromotionType.BUY_TWO_SHIRT_HALF_PRICE_OF_TIE);

        inventory = new Inventory(items, itemPromotions);
        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("Jacket", "Trousers", "Trousers"));
        
        Cart cart = new Cart(inventory);
        cart.add(order);
        
        cart.printInvoice();
	}

}
