package com.domain.utility;

public enum PromotionType {
    MARKED_PRICE(0),
    DISCOUNT_PERCENTAGE(10),
    BUY_TWO_SHIRT_HALF_PRICE_OF_TIE("Shirt");
    
   Object discount;
	
   PromotionType(Object discount) {
	   this.discount = discount;
   }
   
   public Object getDiscount() {
	   return discount;
   }
}
