package com.ingsw.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import com.ingsw.model.Cart;
import com.ingsw.model.Item;

class getTotalPriceTest {

	@Test
	void test() {
		
		Cart cart=new Cart();
		
		Item mockItem1=new Item();
		mockItem1.setPrice(5.0);
		mockItem1.setOnSale(false);
		
		Item mockItem2=new Item();
		mockItem2.setPrice(10.1);
		mockItem2.setOnSale(false);
		
		Item mockItem3=new Item();
		mockItem3.setPrice(15.0);
		mockItem3.setOnSale(false);
		
		cart.setProducts(mockItem1);
		cart.setProducts(mockItem2);
		cart.setProducts(mockItem3);
		
		cart.setShipping(0);
		
		assertEquals(cart.getTotalPrice(), Double.toString(30.1));
	}

}
