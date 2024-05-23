package com.example.onlinebookstore.mapper;

import com.example.onlinebookstore.config.MapperConfig;
import com.example.onlinebookstore.dto.CartItemRequestDto;
import com.example.onlinebookstore.dto.CartItemResponseDto;
import com.example.onlinebookstore.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    @Mapping(source = "bookId", target = "book.id")
    CartItem toEntity(CartItemRequestDto requestDto);

    @Mapping(source = "book.id", target = "bookId")
    CartItemResponseDto toDto(CartItem cartItem);
}
