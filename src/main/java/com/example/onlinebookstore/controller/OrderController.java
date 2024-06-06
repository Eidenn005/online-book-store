package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.dto.OrderItemResponseDto;
import com.example.onlinebookstore.dto.OrderRequestDto;
import com.example.onlinebookstore.dto.OrderResponseDto;
import com.example.onlinebookstore.dto.OrderStatusUpdateDto;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order management", description = "Endpoints for managing orders")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Place an order")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto placeOrder(@AuthenticationPrincipal User user,
                                       @Valid @RequestBody OrderRequestDto orderRequestDto) {
        return orderService.placeOrder(user, orderRequestDto);
    }

    @GetMapping
    @Operation(summary = "Retrieve order history")
    public List<OrderResponseDto> getOrderHistory(@AuthenticationPrincipal User user) {
        return orderService.getOrderHistory(user);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Retrieve a specific order")
    public OrderResponseDto getOrder(@AuthenticationPrincipal User user,
                                     @PathVariable("orderId") Long orderId) {
        return orderService.getOrder(user, orderId);
    }

    @GetMapping("/{orderId}/items/{itemId}")
    @Operation(summary = "Retrieve a specific order item")
    public OrderItemResponseDto getOrderItem(@AuthenticationPrincipal User user,
                                             @PathVariable("orderId") Long orderId,
                                             @PathVariable("itemId") Long itemId) {
        return orderService.getOrderItem(user, orderId, itemId);
    }

    @PatchMapping("/{orderId}")
    @Operation(summary = "Update order status")
    public OrderResponseDto updateOrderStatus(@AuthenticationPrincipal User user,
                                              @PathVariable("orderId") Long orderId,
                                              @Valid @RequestBody OrderStatusUpdateDto statusUpdateDto) {
        return orderService.updateOrderStatus(user, orderId, statusUpdateDto);
    }
}
