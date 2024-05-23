package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.dto.ShoppingCartResponseDto;
import com.example.onlinebookstore.mapper.ShoppingCartMapper;
import com.example.onlinebookstore.model.ShoppingCart;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.repository.ShoppingCartRepository;
import com.example.onlinebookstore.service.ShoppingCartService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCartResponseDto getOrCreate(User user) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findByUserId(user.getId());
        if (shoppingCart.isPresent()) {
            ShoppingCart existedShoppingCart = shoppingCart.get();
            return shoppingCartMapper.toDto(existedShoppingCart);
        }
        ShoppingCart newShoppingCart = new ShoppingCart();
        newShoppingCart.setUser(user);
        newShoppingCart = shoppingCartRepository.save(newShoppingCart);
        return shoppingCartMapper.toDto(newShoppingCart);
    }

    @Override
    public ShoppingCart findById(User user) {
        return shoppingCartRepository.findByUserId(user.getId())
                .orElseGet(() -> createNewShoppingCartForUser(user));
    }

    @Override
    public ShoppingCartResponseDto save(ShoppingCart shoppingCart) {
        shoppingCart = shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    private ShoppingCart createNewShoppingCartForUser(User user) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        return cart;
    }
}

