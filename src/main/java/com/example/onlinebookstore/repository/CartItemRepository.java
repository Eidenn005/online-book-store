package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.CartItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @EntityGraph(value = "book.id")
    CartItem findCartItemByBookId(Long id);

}
