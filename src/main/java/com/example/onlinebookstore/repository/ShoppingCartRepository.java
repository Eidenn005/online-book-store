package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.ShoppingCart;
import com.example.onlinebookstore.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query("FROM ShoppingCart sc JOIN FETCH sc.cartItems WHERE sc.user = :user")
    Optional<ShoppingCart> findCartByUserId(User user);
}
