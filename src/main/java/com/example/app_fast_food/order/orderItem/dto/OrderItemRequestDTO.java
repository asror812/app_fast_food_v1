package com.example.app_fast_food.order.orderItem.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderItemRequestDTO {
    private Long orderId;
    private Long orderItemId;
}
