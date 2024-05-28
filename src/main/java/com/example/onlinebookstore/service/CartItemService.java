package com.example.onlinebookstore.service;

import com.example.onlinebookstore.dto.CartItemRequestDto;
import com.example.onlinebookstore.dto.CartItemResponseDto;
import com.example.onlinebookstore.model.User;

public interface CartItemService {

    CartItemResponseDto add(CartItemRequestDto requestDto, User user);

    CartItemResponseDto updateCartItemQuantity(Long id, CartItemRequestDto requestDto, User user);

    void delete(Long id, User user);
}
