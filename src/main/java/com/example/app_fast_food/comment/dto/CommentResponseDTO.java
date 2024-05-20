package com.example.app_fast_food.comment.dto;

import com.example.app_fast_food.product.Product;
import com.example.app_fast_food.product.dto.ProductResponseDTO;
import com.example.app_fast_food.user.dto.UserResponseDTO;
import com.example.app_fast_food.user.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CommentResponseDTO {

    private UUID id;

    @Max(5) @Min(0)
    private Integer rating;

    @NotNull @NotBlank
    private String comment;

    @NotNull @NotBlank
    private UserResponseDTO user;

    @NotNull @NotBlank
    private ProductResponseDTO product;
}
