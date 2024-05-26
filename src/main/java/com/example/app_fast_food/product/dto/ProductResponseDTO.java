package com.example.app_fast_food.product.dto;

import com.example.app_fast_food.attachment.dto.AttachmentResponseDTO;
import com.example.app_fast_food.attachment.entity.Attachment;
import com.example.app_fast_food.bonus.Bonus;
import com.example.app_fast_food.bonus.dto.BonusResponseDTO;
import com.example.app_fast_food.category.dto.CategoryCreateDTO;
import com.example.app_fast_food.category.dto.CategoryResponseDTO;
import com.example.app_fast_food.category.entity.Category;
import com.example.app_fast_food.comment.Comment;
import com.example.app_fast_food.comment.dto.CommentResponseDTO;
import com.example.app_fast_food.discount.Discount;
import com.example.app_fast_food.discount.dto.DiscountResponseDTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponseDTO {

    private UUID id;

    @NotBlank @NotBlank
    private String name;

    private Double price;

    private CategoryResponseDTO category;

    private Integer weight;

    private Long sold;

    private AttachmentResponseDTO main;

    private AttachmentResponseDTO other;

    private List<CommentResponseDTO> comments;

    private List<DiscountResponseDTO> discounts;
    private List<BonusResponseDTO> bonuses;

    private Long finalPrice;
}
