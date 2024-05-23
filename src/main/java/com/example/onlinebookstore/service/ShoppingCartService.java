package com.example.onlinebookstore.service;

import com.example.onlinebookstore.dto.ShoppingCartResponseDto;
import com.example.onlinebookstore.model.ShoppingCart;
import com.example.onlinebookstore.model.User;

public interface ShoppingCartService {
    ShoppingCartResponseDto getOrCreate(User user);

    ShoppingCart findById(User user);

    ShoppingCartResponseDto save(ShoppingCart shoppingCart);
}
