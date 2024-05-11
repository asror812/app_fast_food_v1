package com.example.app_fast_food.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductDTO {
    @NotBlank
    private String name;

    @NotBlank
    private Double price;

    @NotBlank
    private String categoryName;

    @NotBlank
    private Integer weight;
}
