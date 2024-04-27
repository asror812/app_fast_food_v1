package com.example.app_fast_food.user.dto;

import com.example.app_fast_food.check.entity.Check;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDTO {

    @Column(unique = true)
    @NotBlank @NotNull
    private String phoneNumber;

    @NotBlank @NotNull
    private String name;

    @NotBlank  @NonNull
    private String password;

/*    @Enumerated(EnumType.STRING)
    private StatusEnum status;*/

    private List<Check> check;
}
