package com.example.app_fast_food.check.entity;


import com.example.app_fast_food.filial.entity.Filial;
import com.example.app_fast_food.order.Order;
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

      @OneToOne()
      private Order order;

      @ManyToOne
      @JoinColumn(name = "user_id" )
      private User user;

      @NotNull @NotBlank
      private Long totalAmount;

      @NotNull @NotBlank
      private Long totalDiscount;

      @NotNull @NotBlank
      private Long totalPrice;

      @OneToOne
      @JoinColumn(name = "filial_id" )
      private Filial filial;

      private String courier;


}
