package com.example.onlinebookstore.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private String author;
    private String isbn;
    private String description;
    private String coverImage;
}