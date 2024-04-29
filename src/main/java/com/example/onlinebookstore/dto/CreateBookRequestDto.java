package com.example.onlinebookstore.dto;

import java.math.BigDecimal;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CreateBookRequestDto {
    private String title;
    private BigDecimal price;
    private String author;
    private String isbn;
    private String description;
    private String coverImage;
}
