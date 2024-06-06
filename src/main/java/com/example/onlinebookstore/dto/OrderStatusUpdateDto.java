package com.example.onlinebookstore.dto;

import com.example.onlinebookstore.model.Status;
import lombok.Data;

@Data
public class OrderStatusUpdateDto {
    private Status status;
}
