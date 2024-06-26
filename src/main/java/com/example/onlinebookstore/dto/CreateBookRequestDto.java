package com.example.onlinebookstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

@Data
public class CreateBookRequestDto {
    @NotBlank
    private String title;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @NotBlank
    @Size(min = 5, max = 100)
    private String author;
    @ISBN
    private String isbn;
    private String description;
    private String coverImage;
    @NotEmpty
    private Set<Long> categoryIds;
}
