package com.example.app_fast_food.product.dto;

import com.example.app_fast_food.category.entity.Category;
import com.example.app_fast_food.product.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NoArgsConstructor;
import java.util.UUID;

@NoArgsConstructor
@JsonIgnoreProperties = true)
public class ProductResponseDTO extends ProductDTO {

    private UUID id;

    private Product product_id;

    private Integer requiredQuantity;

    private Long sold;

    private Category category;
}
