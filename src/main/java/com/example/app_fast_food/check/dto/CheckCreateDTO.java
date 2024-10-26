package com.example.app_fast_food.check.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckCreateDTO {

    private UUID orderId;

    @NotBlank @NotNull
    private Long totalAmount;

    @NotBlank @NotNull
    private Long totalDiscount;

    @NotBlank @NotNull
    private Long totalPrice;

    @NotBlank @NotNull
    private Double longitude;

    @NotBlank @NotNull
    private Double latitude;

}
