package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.dto.ShoppingCartDto;
import com.example.onlinebookstore.exception.EntityNotFoundException;
import com.example.onlinebookstore.mapper.ShoppingCartMapper;
import com.example.onlinebookstore.model.ShoppingCart;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.repository.ShoppingCartRepository;
import com.example.onlinebookstore.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    @Transactional
    public ShoppingCartDto createForUser(User user) {
        ShoppingCart newShoppingCart = new ShoppingCart();
        newShoppingCart.setUser(user);
        newShoppingCart = shoppingCartRepository.save(newShoppingCart);
        return shoppingCartMapper.toDto(newShoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart findByUserId(User user) {
        logger.info("Finding shopping cart for user ID: {}", user.getId());
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findByUser(user);
        if (shoppingCart.isPresent()) {
            return shoppingCart.get();
        } else {
            logger.error("Shopping cart not found for user ID: {}", user.getId());
            throw new EntityNotFoundException("Not found!");
        }
    }

    @Override
    public ShoppingCartDto getCart(User user) {
        ShoppingCart cart = findByUserId(user);
        return shoppingCartMapper.toDto(cart);
    }
}
