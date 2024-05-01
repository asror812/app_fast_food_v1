package com.example.app_fast_food.user.dto;

import com.example.app_fast_food.check.entity.Check;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private String phoneNumber;

    @NotBlank  @NonNull
    private String password;

    private LocalDate dateOfBirth;

/*    @Enumerated(EnumType.STRING)
    private StatusEnum status;*/
}
