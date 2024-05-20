package com.example.app_fast_food.order.orderItem.dto;

import com.example.app_fast_food.product.dto.ProductResponseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderItemResponseDTO  {


    private Long id;

    @NotNull @NotBlank
    private Integer quantity;

    private ProductResponseDTO product;

}
