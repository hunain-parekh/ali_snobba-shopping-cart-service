package com.ali_snobba.shoppingcartservice;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.mockito.BDDMockito.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ali_snobba.shoppingcartservice.Controller.CartController;
import com.ali_snobba.shoppingcartservice.Model.Cart;
import com.ali_snobba.shoppingcartservice.Repository.ICartRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingCartServiceControllerTests {
    
    @Autowired
    private MockMvc mvc;

    @Mock
    private ICartRepository cartRepo;

	@InjectMocks
	private CartController cartController;

	private JacksonTester<Cart> jsonCart;
	private JacksonTester<List<Cart>> jsonCarts;

	@BeforeEach
	public void setUp(){
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(cartController).build();
	}

    @Test
	public void cansaveProductInCartSuccessfully() throws Exception {
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
		mvc.perform(post("/api/cart")
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonCart.write(cart).getJson()))
			.andExpect(status().isOk());
		verify(cartRepo, times(1)).save(cart);
	}

    @Test
	public void canGetAllListOfCart() throws Exception{
		Long myId = 1L;
		Long myProductId = 1L;
		Integer myQuantity = 10;
		Long myTotalPrice = 10000000L;
		Cart cart1 = Cart.builder()
							.id(myId)
							.productId(myProductId)
							.quantity(myQuantity)
							.totalPrice(myTotalPrice)
							.build();
        Cart cart2 = Cart.builder()
                            .id(myId)
                            .productId(myProductId)
                            .quantity(myQuantity)
                            .totalPrice(myTotalPrice)
                            .build();
		given(cartRepo.findAll()).willReturn(List.of(cart1, cart2));
		mvc.perform(get("/api/cart/all")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().json(jsonCarts.write(List.of(cart1, cart2)).getJson()));
	}

       
    @Test
    public void canDeleteACartById() throws Exception{
        Long id = 1L;
        ResultActions response = mvc.perform(delete("/api/cart/{id}", id));
        response.andExpect(status().isOk());
        verify(cartRepo,times(1)).deleteById(id);
    }

    @Test
    public void canDeleteAllInCart() throws Exception{
        mvc.perform(delete("/api/cart/all")).andExpect(status().isOk());
        verify(cartRepo,times(1)).deleteAll();
    }


}
