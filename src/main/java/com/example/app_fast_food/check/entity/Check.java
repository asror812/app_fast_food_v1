package com.example.app_fast_food.check.entity;


import com.example.app_fast_food.filial.entity.Filial;
import com.example.app_fast_food.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "`check`")
public class Check {
      @Id
      @GeneratedValue(strategy = GenerationType.UUID)
      private UUID id;

      @ManyToOne
      @JoinColumn(name = "user_id" )
      private User user;

      @OneToOne
      @JoinColumn(name = "filial_id" )
      private Filial filial;

      @NotNull @NotBlank
      private Long totalPrice;

      private String courier;
}
