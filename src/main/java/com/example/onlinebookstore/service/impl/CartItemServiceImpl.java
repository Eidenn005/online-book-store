package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.dto.CartItemRequestDto;
import com.example.onlinebookstore.dto.CartItemResponseDto;
import com.example.onlinebookstore.exception.EntityNotFoundException;
import com.example.onlinebookstore.mapper.CartItemMapper;
import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.model.CartItem;
import com.example.onlinebookstore.model.ShoppingCart;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.repository.CartItemRepository;
import com.example.onlinebookstore.service.CartItemService;
import com.example.onlinebookstore.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final ShoppingCartService shoppingCartService;
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public CartItemResponseDto add(CartItemRequestDto requestDto, User user) {
        ShoppingCart shoppingCart = shoppingCartService.findByUserId(user);
        CartItem cartItem = cartItemMapper.toEntity(requestDto);
        Book book = bookRepository.findById(requestDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book not found with id: " + requestDto.getBookId()));
        cartItem.setBook(book);
        cartItem.setShoppingCart(shoppingCart);
        cartItemRepository.save(cartItem);
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    @Transactional
    public CartItemResponseDto updateCartItemQuantity(
            Long id,
            CartItemRequestDto requestDto,
            User user
    ) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("CartItem not found with id: " + id));

        checkCartItemByUser(user, cartItem);
        cartItem.setQuantity(requestDto.getQuantity());
        cartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.toDto(cartItem);
    }

    @Override
    @Transactional
    public void delete(Long id, User user) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("CartItem not found with id: " + id));

        checkCartItemByUser(user, cartItem);

        cartItemRepository.delete(cartItem);
    }

    private void checkCartItemByUser(User user, CartItem cartItem) {
        if (!cartItem.getShoppingCart().getUser().getId().equals(user.getId())) {
            throw new EntityNotFoundException("User: " + user + "do not have cartItem:" + cartItem);
        }
    }
}
