package com.example.app_fast_food.comment.dto;

import com.example.app_fast_food.product.Product;
import com.example.app_fast_food.user.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.UUID;

public class CommentUpdateDTO {

    @Max(5) @Min(0)
    private Integer rating;


}
