package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.dto.CartItemRequestDto;
import com.example.onlinebookstore.dto.CartItemResponseDto;
import com.example.onlinebookstore.mapper.CartItemMapper;
import com.example.onlinebookstore.model.CartItem;
import com.example.onlinebookstore.model.ShoppingCart;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.repository.CartItemRepository;
import com.example.onlinebookstore.service.CartItemService;
import com.example.onlinebookstore.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final ShoppingCartService shoppingCartService;
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public CartItemResponseDto add(CartItemRequestDto requestDto, User user) {
        ShoppingCart shoppingCart = shoppingCartService.findById(user);
        CartItem cartItem = cartItemMapper.toEntity(requestDto);
        cartItem.setShoppingCart(shoppingCart);
        cartItemRepository.save(cartItem);
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    @Transactional
    public CartItemResponseDto updateBooksQuantity(Long id, CartItemRequestDto requestDto, User user) {
        CartItem cartItem = cartItemRepository.findByBookId(requestDto.getBookId());
        cartItem.setQuantity(requestDto.getQuantity());
        cartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    @Transactional
    public void delete(CartItemRequestDto requestDto) {
        CartItem cartItem = cartItemRepository.findByBookId(requestDto.getBookId());
        cartItemRepository.delete(cartItem);
    }
}
