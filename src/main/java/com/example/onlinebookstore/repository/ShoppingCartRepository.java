package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.ShoppingCart;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query("FROM ShoppingCart s JOIN FETCH s.cartItems WHERE s.user.id = :userId")
    Optional<ShoppingCart> findByUserId(@Param("userId") Long userId);
}
