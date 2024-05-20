package com.example.app_fast_food.order.orderItem.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderItemCreateDTO {


    private UUID productId;


}
