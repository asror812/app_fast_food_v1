package com.example.app_fast_food.user.otp.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ValidatePhoneNumberDTO {
    @NotBlank
    @Pattern(regexp = "^\\+998[0-9]{9}$")
    private String phoneNumber;
    private Integer otp;
}
