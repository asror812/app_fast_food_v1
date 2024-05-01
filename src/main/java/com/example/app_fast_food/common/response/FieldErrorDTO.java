package com.example.app_fast_food.common.response;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class FieldErrorDTO {

    private String field;
    private String message;
}
