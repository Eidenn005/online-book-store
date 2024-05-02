package com.example.onlinebookstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotNull
    private String title;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @NotNull
    @Min(5)
    private String author;
    @NotNull
    @Min(13)
    private String isbn;
    private String description;
    private String coverImage;
}
