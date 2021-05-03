package com.domain.service;


import com.domain.Offer.AbstractApply;
import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Inventory;
import com.domain.model.Product;
import com.domain.utility.PromotionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Cart implements ICart {

    private List<Product> items = Collections.synchronizedList(new ArrayList());
    private Inventory inventory;

    public Cart(Inventory inventory) {
        this.inventory = inventory;
    }

    public void empty() {
        this.items.clear();
    }

    public List<Product> getItems() {
        return items;
    }

    public void add(String itemName) throws ItemNotSameTypeException {
        Product inventoryItem = inventory.getListingItems().get(itemName);
        items.add(new Product(inventoryItem));
    }

    public void add(List<String> itemNames) {

        itemNames.forEach((itemName) -> {
            try {
                add(itemName);
            } catch (ItemNotSameTypeException e) {
                e.printStackTrace();
            }
        });
    }

	@Override
	public double calculateFinalPrice(String name, List<Product> productList, Integer discount) throws ItemNotSameTypeException {
		// Apply discount and calculate Final price
		return inventory.getApply().get(name).filterPrice(productList,discount);
	}

	@Override
	public double calculateMarkerPrice(List<Product> productList)
			throws ItemNotSameTypeException {
		// Return Marked price of Product Name
		 return inventory.defaultNormalHelper().filterPrice(productList, null);
	}
	
	@Override
	public void printInvoice() throws ItemNotSameTypeException{
		Map<String,List<Product>> prodMap = items.stream().collect(Collectors.groupingBy(Product::getName));
		Map<String,Double> discountMap = new HashMap<>();
		Map<String,PromotionType> promotionTypeMap = inventory.getPromotions();
		
		Double markedPrice = 0.0;
		Double finalPrice = 0.0;
		
		for(Map.Entry<String, List<Product>> entry:prodMap.entrySet()) {
			markedPrice += calculateMarkerPrice(entry.getValue());
			Integer discount = 0;
			Object value = promotionTypeMap.get(entry.getKey()).getDiscount();
			
			if(value !=null && value.equals("Shirt")) {
				discount = prodMap.get(value) !=null ? prodMap.get(value).size():0;
			}else if(value !=null){
				discount = (Integer) value;
			}
			finalPrice += calculateFinalPrice(entry.getKey(),entry.getValue(), discount);
				
			Double priceDiff = calculateMarkerPrice(entry.getValue()) - calculateFinalPrice(entry.getKey(),entry.getValue(), discount);
			if(priceDiff !=0)
				discountMap.put(entry.getKey(), priceDiff);			
		}
		
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("SubTotal: $"+markedPrice.floatValue());
		if(!discountMap.isEmpty()) {
		discountMap.entrySet().stream().forEach(e-> System.out.println(e.getKey()+" "+promotionTypeMap.get(e.getKey()).getDiscount()+"% off: -$"+e.getValue().floatValue()));
		}else {
			System.out.println("(No Offer available)");
		}
		System.out.println("Total: $"+finalPrice.floatValue());
		System.out.println("------------------------------------------------------------------------------------------");
	}

   
}
