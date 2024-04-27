package com.example.app_fast_food.filial.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class FilialDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String destination;

    private Date openAt;

    private Date closeAt;

    private Double longitude;

    @NotBlank
    private Double latitude;
}
