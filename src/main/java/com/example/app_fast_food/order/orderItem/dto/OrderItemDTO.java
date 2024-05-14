package com.example.app_fast_food.order.orderItem.dto;

import com.example.app_fast_food.order.Order;
import com.example.app_fast_food.product.Product;
import com.example.app_fast_food.product.dto.ProductDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderItemDTO {

    @NotBlank
    @NotNull
    private ProductDTO product;

    @NotNull
    @NotBlank
    private Integer quantity;

    private Order order;

}
