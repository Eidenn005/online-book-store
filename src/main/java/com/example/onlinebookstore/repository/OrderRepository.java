package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.Order;
import com.example.onlinebookstore.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = {"orderItems"})
    List<Order> findByUser(User user);

    Optional<Order> findByIdAndUser(Long orderId, User user);
}
