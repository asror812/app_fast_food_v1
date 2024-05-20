package com.example.app_fast_food.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class SignInDTO {

    @NotBlank @NotNull
    @Pattern(regexp = "^\\+[0-9]{2} [0-9]{3}-[0-9]{2}-[0-9]{2}$")
    private String phoneNumber;

    @NotBlank  @NotNull
    private String password;
}
