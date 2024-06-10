package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.OrderItem;
import com.example.onlinebookstore.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findByIdAndOrder_IdAndOrder_User(Long itemId, Long orderId, User user);
}
