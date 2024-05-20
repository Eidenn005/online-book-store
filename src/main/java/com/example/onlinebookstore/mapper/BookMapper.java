package com.example.onlinebookstore.mapper;

import com.example.onlinebookstore.config.MapperConfig;
import com.example.onlinebookstore.dto.BookDto;
import com.example.onlinebookstore.dto.BookDtoWithoutCategoryIds;
import com.example.onlinebookstore.dto.CreateBookRequestDto;
import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.model.Category;
import java.util.Collections;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    @Mapping(target = "categories", ignore = true)
    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Book toEntity(CreateBookRequestDto createBookRequestDto);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        if (book.getCategories() == null) {
            bookDto.setCategories(Collections.emptySet());
        }
        bookDto.setCategories(book.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet()));
    }
}
