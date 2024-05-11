package com.example.app_fast_food.bonus.dto;

import com.example.app_fast_food.bonus.BonusCondition;
import com.example.app_fast_food.product.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BonusDTO {

    private ProductDTO bonusProduct;

    private ProductDTO product;

    private BonusCondition conditionType;

    private double conditionValue;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean isActive;
}
