package com.example.onlinebookstore.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemResponseDto {
    private Long id;
    private Long bookId;
    private int quantity;
    private BigDecimal price;
}
