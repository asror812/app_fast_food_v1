package com.example.app_fast_food.order.dto;


import com.example.app_fast_food.order.orderItem.dto.OrderItemResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderResponseDTO  extends OrderDTO {

    private List<OrderItemResponseDTO> orderItems;
    private Long shippingCost ;
    private Long orderPrice;
    private Long totalPrice;

}
