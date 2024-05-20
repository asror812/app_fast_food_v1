package com.example.app_fast_food.user.dto;

import com.example.app_fast_food.check.entity.Check;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDTO {

    private String name;

    @NotBlank @NotNull
    @Pattern(regexp = "^\\+[0-9]{2} [0-9]{3}-[0-9]{2}-[0-9]{2}$")
    private String phoneNumber;

    @NotBlank @NotNull
    private String password;

    private LocalDate dateOfBirth;

}
