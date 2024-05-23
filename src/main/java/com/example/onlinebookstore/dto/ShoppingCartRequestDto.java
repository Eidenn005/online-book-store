package com.example.onlinebookstore.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Set;
import lombok.Data;

@Data
public class ShoppingCartRequestDto {
    @NotNull(message = "User ID cannot be null")
    private Long userId;
    @NotNull(message = "Cart items cannot be null")
    private Set<CartItemRequestDto> cartItems;
}
