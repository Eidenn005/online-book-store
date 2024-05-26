package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.dto.CategoryDto;
import com.example.onlinebookstore.dto.CreateCategoryRequestDto;
import com.example.onlinebookstore.exception.EntityNotFoundException;
import com.example.onlinebookstore.mapper.BookMapper;
import com.example.onlinebookstore.mapper.CategoryMapper;
import com.example.onlinebookstore.model.Category;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.repository.CategoryRepository;
import com.example.onlinebookstore.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public CategoryDto save(CreateCategoryRequestDto requestDto) {
        Category category = categoryMapper.toModel(requestDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can`t find a category with id: " + id));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto update(Long id, CreateCategoryRequestDto requestDto) {
        validateCategoryExistence(id);
        Category updateCategory = categoryMapper.toModel(requestDto);
        updateCategory.setId(id);
        return categoryMapper.toDto(categoryRepository.save(updateCategory));
    }

    @Override
    public void deleteById(Long id) {
        validateCategoryExistence(id);
        categoryRepository.deleteById(id);
    }

    private void validateCategoryExistence(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Can`t find a category with id: " + id);
        }
    }
}
