package com.example.app_fast_food.product.dto;

import com.example.app_fast_food.bonus.dto.BonusResponseDTO;
import com.example.app_fast_food.category.entity.Category;
import com.example.app_fast_food.discount.dto.DiscountResponseDTO;
import com.example.app_fast_food.product.entity.Product;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO extends ProductDTO {

    private UUID id;

    private Long sold;

    private Category category;

    private String image;
    private List<DiscountResponseDTO> discounts;
    private List<BonusResponseDTO> bonuses;

    private Double resultPrice;
}
