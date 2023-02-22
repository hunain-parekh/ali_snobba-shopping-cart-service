package com.ali_snobba.shoppingcartservice.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ali_snobba.shoppingcartservice.Model.Cart;
import com.ali_snobba.shoppingcartservice.Repository.ICartRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private ICartRepository cartRepo;

    @PostMapping
    public void saveProductInCart(@RequestBody Cart cart){
        cartRepo.save(cart);
    }
    
    @GetMapping("/")
    public List<Cart> getAllCart(){
        return cartRepo.findAll();
    }
    
    @DeleteMapping("{/id}")
    public void deleteProductFromCartById(@PathVariable Long id){
        cartRepo.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllProductsFromCart(){
        cartRepo.deleteAll();
    }
}
