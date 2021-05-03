package com.domain.service;

import com.domain.model.Inventory;
import com.domain.model.Product;
import com.domain.utility.PromotionType;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {
    private Cart cart;
    private Inventory inventory;

    @Before
    public void setUp() {
        List<Product> items = Arrays.asList(new Product("Trousers", 35.5), new Product("Shirt", 12.50), 
        		new Product("Tie", 9.5), new Product("Jacket", 49.9));
        List<PromotionType> itemPromotions = Arrays.asList(PromotionType.DISCOUNT_PERCENTAGE, PromotionType.MARKED_PRICE,
        		PromotionType.BUY_TWO_SHIRT_HALF_PRICE_OF_TIE, PromotionType.MARKED_PRICE);

        inventory = new Inventory(items, itemPromotions);
    }

    @Given("^The shopping basket has (\\d+) Apple, (\\d+) Banana, (\\d+) Melon, (\\d+) Lime$")
    public void the_shopping_basket_has_Apple_Banana_Melon_Lime(int arg1, int arg2, int arg3, int arg4) throws Throwable {

    	List<String> addItems = new CopyOnWriteArrayList<String>(Arrays.asList("Jacket", "Trousers", "Trousers", "Shirt", "Tie", "Trousers", "Shirt", "Shirt", "Tie", "Tie"));
        cart = new Cart(inventory);
        cart.add(addItems);
    }

    @When("^I calculate the final price$")
    public void i_calculate_the_final_price() throws Throwable {
        cart.calculateFinalPrice(null,null,null);
    }

    @Then("^The price should show (\\d+.\\d+)$")
    public void the_price_should_show(double arg1) throws Throwable {
        assertEquals(cart.calculateFinalPrice(null,null,null), arg1, 0.01);
    }

    @Given("^The shopping basket has (\\d+) Apple$")
    public void the_shopping_basket_has_Apple(int arg1) throws Throwable {
        List<String> addItems = Arrays.asList("Apple");

        cart = new Cart(inventory);
        cart.add(addItems);
    }

    @Given("^The shopping basket has (\\d+) Apple and (\\d+) banana$")
    public void the_shopping_basket_has_Apple_and_banana(int arg1, int arg2) throws Throwable {
        List<String> addItems = Arrays.asList("Apple", "Banana", "Banana");

        cart = new Cart(inventory);
        cart.add(addItems);
    }

    @Given("^The shopping basket has (\\d+) Apple and (\\d+) banana and (\\d+) melon$")
    public void the_shopping_basket_has_Apple_and_banana_and_melon(int arg1, int arg2, int arg3) throws Throwable {
        List<String> addItems = Arrays.asList("Apple", "Banana", "Banana", "Melon", "Melon", "Melon");

        cart = new Cart(inventory);
        cart.add(addItems);
    }
}
