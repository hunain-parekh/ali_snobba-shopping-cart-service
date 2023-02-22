package com.ali_snobba.shoppingcartservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ali_snobba.shoppingcartservice.Model.Cart;

public interface ICartRepository extends JpaRepository<Cart,Long>{
    
}
