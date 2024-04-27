package com.example.app_fast_food.check.dto;

import com.example.app_fast_food.filial.entity.Filial;
import com.example.app_fast_food.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CheckDTO {

    @ManyToOne
    @JoinColumn(name = "user_id" )
    private User userId;

    @OneToOne
    @JoinColumn(name = "filial_id" )
    private Filial filialId;

    private Double totalPrice;

    private String courier;

}
