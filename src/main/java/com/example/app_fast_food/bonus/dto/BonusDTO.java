package com.example.app_fast_food.bonus.dto;

import com.example.app_fast_food.bonus.BonusCondition;
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

    private BonusCondition conditionType;

    private double conditionValue;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean isActive;
}
