package com.example.onlinebookstore.dto;

import com.example.onlinebookstore.model.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderStatusUpdateDto {
    private Status status;
}
