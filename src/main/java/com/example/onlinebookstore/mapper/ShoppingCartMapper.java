package com.example.onlinebookstore.mapper;

import com.example.onlinebookstore.config.MapperConfig;
import com.example.onlinebookstore.dto.ShoppingCartRequestDto;
import com.example.onlinebookstore.dto.ShoppingCartResponseDto;
import com.example.onlinebookstore.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface ShoppingCartMapper {
    @Mapping(source = "userId", target = "user.id")
    ShoppingCart toEntity(ShoppingCartRequestDto requestDto);

    @Mapping(source = "user.id", target = "userId")
    ShoppingCartResponseDto toDto(ShoppingCart shoppingCart);
}
