package com.example.app_fast_food.order.orderItem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class OrderItemUpdateDTO {

    @NotNull @NotBlank
    private Integer quantity;

    private UUID productId;

}
