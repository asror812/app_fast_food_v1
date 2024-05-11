package com.example.app_fast_food.discount.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class DiscountResponseDTO {


    private UUID id;

    private String  name;
    private Integer percentage;
    private LocalDate until;

}
