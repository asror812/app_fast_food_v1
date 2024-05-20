package com.example.app_fast_food.order.dto;

import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class OrderCreateDTO extends OrderDTO {

    private UUID orderId;
}
