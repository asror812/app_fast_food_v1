package com.example.app_fast_food.product.dto;

import com.example.app_fast_food.attachment.entity.Attachment;
import com.example.app_fast_food.category.entity.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class ProductCreateDTO {

    @NotNull @NotBlank
    private String name;

    private Double price;

    @NotNull @NotBlank
    private Category category;

    private Integer weight;


    private Attachment main;

    private Attachment other;

}
