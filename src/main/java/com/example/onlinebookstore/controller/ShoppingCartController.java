package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.dto.CartItemRequestDto;
import com.example.onlinebookstore.dto.CartItemResponseDto;
import com.example.onlinebookstore.dto.ShoppingCartDto;
import com.example.onlinebookstore.mapper.ShoppingCartMapper;
import com.example.onlinebookstore.model.ShoppingCart;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.service.CartItemService;
import com.example.onlinebookstore.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management", description = "Endpoints for managing cart")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final CartItemService cartItemService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @Operation
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CartItemResponseDto addItem(
            @Valid @RequestBody CartItemRequestDto requestDto,
            @AuthenticationPrincipal User user
    ) {
        return cartItemService.add(requestDto, user);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ShoppingCartDto getCart(@AuthenticationPrincipal User user) {
        return shoppingCartService.getCart(user);
    }

    @PutMapping("/cart-items/{id}")
    @PreAuthorize("hasRole('USER')")
    public CartItemResponseDto updateCartItems(
            @PathVariable Long id,
            @Valid @RequestBody CartItemRequestDto requestDto,
            @AuthenticationPrincipal User user
    ) {
        return cartItemService.updateCartItemQuantity(id, requestDto, user);
    }

    @DeleteMapping("/cart-items/{cartItemId}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartItem(@Valid @RequestBody CartItemRequestDto requestDto) {
        cartItemService.delete(requestDto);
    }
}
