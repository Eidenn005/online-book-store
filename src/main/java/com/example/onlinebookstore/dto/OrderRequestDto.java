package com.example.onlinebookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderRequestDto {
    @NotBlank
    @Size(max = 1000)
    private String shippingAddress;
}
