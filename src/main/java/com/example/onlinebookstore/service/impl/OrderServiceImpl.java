package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.dto.OrderItemResponseDto;
import com.example.onlinebookstore.dto.OrderRequestDto;
import com.example.onlinebookstore.dto.OrderResponseDto;
import com.example.onlinebookstore.dto.OrderStatusUpdateDto;
import com.example.onlinebookstore.exception.EntityNotFoundException;
import com.example.onlinebookstore.exception.UnauthorizedAccessException;
import com.example.onlinebookstore.mapper.OrderItemMapper;
import com.example.onlinebookstore.mapper.OrderMapper;
import com.example.onlinebookstore.model.Order;
import com.example.onlinebookstore.model.OrderItem;
import com.example.onlinebookstore.model.ShoppingCart;
import com.example.onlinebookstore.model.Status;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.repository.OrderItemRepository;
import com.example.onlinebookstore.repository.OrderRepository;
import com.example.onlinebookstore.service.OrderService;
import com.example.onlinebookstore.service.ShoppingCartService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ShoppingCartService shoppingCartService;

    @Override
    @Transactional
    public OrderResponseDto placeOrder(User user, OrderRequestDto orderRequestDto) {
        ShoppingCart cart = shoppingCartService.findByUserId(user);

        Order order = new Order();
        order.setUser(user);
        order.setStatus(Status.PENDING);
        order.setOrderDate(LocalDateTime.now());
        order.setShippingAddress(orderRequestDto.getShippingAddress());

        Set<OrderItem> orderItems = getOrderItems(cart, order);
        order.setOrderItems(orderItems);

        BigDecimal total = calculateTotalPrice(orderItems);
        order.setTotal(total);

        Order savedOrder = orderRepository.save(order);
        cart.getCartItems().clear();
        return orderMapper.toDto(savedOrder);
    }

    private BigDecimal calculateTotalPrice(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Set<OrderItem> getOrderItems(ShoppingCart cart, Order order) {
        return cart.getCartItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setBook(cartItem.getBook());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPrice(cartItem.getBook().getPrice()
                            .multiply(BigDecimal.valueOf(cartItem.getQuantity())));
                    return orderItem;
                })
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDto> getOrderHistory(User user) {
        List<Order> orders = orderRepository.findByUser(user);
        return orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponseDto getOrder(User user, Long orderId) {
        Order order = orderRepository.findByIdAndUser(orderId, user)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        validateUserOrder(user, order);
        return orderMapper.toDto(order);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderItemResponseDto getOrderItem(User user, Long orderId, Long itemId) {
        Order order = getOrderById(orderId);
        validateUserOrder(user, order);
        OrderItem orderItem = orderItemRepository
                .findByIdAndOrder_IdAndOrder_User(itemId, orderId, user)
                .orElseThrow(() -> new EntityNotFoundException("Order item not found"));
        return orderItemMapper.toDto(orderItem);
    }

    @Override
    @Transactional
    public OrderResponseDto updateOrderStatus(User user, Long orderId, OrderStatusUpdateDto statusUpdateDto) {
        Order order = getOrderById(orderId);
        validateUserOrder(user, order);
        order.setStatus(statusUpdateDto.getStatus());
        order = orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    private void validateUserOrder(User user, Order order) {
        if (!order.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedAccessException("You do not have permission to access this order item");
        }
    }

    private Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }
}
