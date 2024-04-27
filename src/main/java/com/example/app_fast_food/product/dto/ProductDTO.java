package com.example.app_fast_food.product.dto;

import com.example.app_fast_food.product.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

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
