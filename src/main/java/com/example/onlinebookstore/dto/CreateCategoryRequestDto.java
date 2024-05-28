package com.example.onlinebookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCategoryRequestDto {
    @NotBlank
    @Size(min = 4)
    private String name;
    @NotBlank
    @Size(max = 1000)
    private String description;
}
