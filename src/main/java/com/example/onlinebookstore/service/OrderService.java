package com.example.onlinebookstore.service;

import com.example.onlinebookstore.dto.OrderItemResponseDto;
import com.example.onlinebookstore.dto.OrderRequestDto;
import com.example.onlinebookstore.dto.OrderResponseDto;
import com.example.onlinebookstore.dto.OrderStatusUpdateDto;
import com.example.onlinebookstore.model.User;
import java.util.List;

public interface OrderService {
    OrderResponseDto placeOrder(User user, OrderRequestDto orderRequestDto);

    List<OrderResponseDto> getOrderHistory(User user);

    OrderResponseDto getOrder(User user, Long orderId);

    OrderItemResponseDto getOrderItem(User user, Long orderId, Long itemId);

    OrderResponseDto updateOrderStatus(User user, Long orderId, OrderStatusUpdateDto statusUpdateDto);
}
