package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.dto.BookDto;
import com.example.onlinebookstore.dto.CreateBookRequestDto;
import com.example.onlinebookstore.exception.EntityNotFoundException;
import com.example.onlinebookstore.mapper.BookMapper;
import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto createBookRequestDto) {
        Book book = bookMapper.toModel(createBookRequestDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto findById(long id) {
        Book book = findBookByIdOrThrow(id);
        return bookMapper.toDto(book);
    }

    @Override
    public BookDto updateById(Long id, CreateBookRequestDto createBookRequestDto) {
        if (!validateExistence(id)) {
            throw new EntityNotFoundException("Cant update book by id: " + id);
        }
        Book book = bookMapper.toModel(createBookRequestDto);
        book.setId(id);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    @Override
    public void deleteById(Long id) {
        if (!validateExistence(id)) {
            throw new EntityNotFoundException("Cant delete book by id: " + id);
        }
        bookRepository.deleteById(id);
    }

    private boolean validateExistence(Long id) {
        return bookRepository.existsById(id);
    }

    private Book findBookByIdOrThrow(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book not found by id: " + id)
        );
    }
}
