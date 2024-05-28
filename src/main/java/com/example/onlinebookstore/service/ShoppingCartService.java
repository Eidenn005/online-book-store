package com.example.onlinebookstore.service;

import com.example.onlinebookstore.dto.ShoppingCartDto;
import com.example.onlinebookstore.model.ShoppingCart;
import com.example.onlinebookstore.model.User;

public interface ShoppingCartService {

    ShoppingCartDto createForUser(User user);

    ShoppingCart findByUserId(User user);

    ShoppingCartDto getCart(User user);
}
