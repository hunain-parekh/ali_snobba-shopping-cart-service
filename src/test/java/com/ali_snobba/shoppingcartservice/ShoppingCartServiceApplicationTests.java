package com.ali_snobba.shoppingcartservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ali_snobba.shoppingcartservice.Model.Cart;

@SpringBootTest
class ShoppingCartServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void getAndSetProductID(){
		Cart cart = new Cart();
		Long myId = 1L;
		cart.setId(myId);
		assertEquals(myId, cart.getId());
	}

	@Test
	void getAndSetProductId(){
		Cart cart = new Cart();
		Long myProductId = 1L;
		cart.setProductId(myProductId);
		assertEquals(myProductId, cart.getProductId());
	}

	@Test
	void getAndSetQuantity(){
		Cart cart = new Cart();
		Integer myQuantity = 10;
		cart.setQuantity(myQuantity);
		assertEquals(myQuantity, cart.getQuantity());
	}

	@Test
	void getAndSetTotalPrice(){
		Cart cart = new Cart();
		Long myTotalPrice = 10000000L;
		cart.setTotalPrice(myTotalPrice);
		assertEquals(myTotalPrice, cart.getTotalPrice());
	}

	@Test
	void allArgsConstructorCart(){
		Long myId = 1L;
		Long myProductId = 1L;
		Integer myQuantity = 10;
		Long myTotalPrice = 10000000L;
		Cart cart = new Cart(myId,myProductId,myQuantity,myTotalPrice);
		assertEquals(myId, cart.getId());
		assertEquals(myProductId, cart.getProductId());
		assertEquals(myQuantity, cart.getQuantity());
		assertEquals(myTotalPrice, cart.getTotalPrice());
	}

	@Test
	void builderProduct(){
		Long myId = 1L;
		Long myProductId = 1L;
		Integer myQuantity = 10;
		Long myTotalPrice = 10000000L;
		Cart cart = Cart.builder()
							.id(myId)
							.productId(myProductId)
							.quantity(myQuantity)
							.totalPrice(myTotalPrice)
							.build();
		assertEquals(myId, cart.getId());
		assertEquals(myProductId, cart.getProductId());
		assertEquals(myQuantity, cart.getQuantity());
		assertEquals(myTotalPrice, cart.getTotalPrice());
	}
}
