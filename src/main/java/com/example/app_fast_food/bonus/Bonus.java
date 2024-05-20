package com.example.app_fast_food.bonus;


import com.example.app_fast_food.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bonus")
public class Bonus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "bonuses")
    private Set<Product> bonusProduct;

    @Enumerated(EnumType.STRING)
    private BonusCondition conditionType;

    private double conditionValue;

    private LocalDate startDate;
    private LocalDate endDate;

    private boolean isActive;

}
