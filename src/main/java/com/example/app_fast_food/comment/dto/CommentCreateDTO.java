package com.example.app_fast_food.comment.dto;

import com.example.app_fast_food.product.Product;
import com.example.app_fast_food.user.entity.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class CommentCreateDTO {

    @Max(5) @Min(0)
    private Integer rating;


    private User user;

    private Product product;
}
