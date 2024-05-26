package com.example.app_fast_food.bonus;


import com.example.app_fast_food.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bonus")
@SQLRestriction("is_active=true")
@SQLDelete(sql = ("update bonus set is_active=false where id=?"))
public class Bonus {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToMany(mappedBy = "bonuses" , fetch = FetchType.LAZY , cascade = {CascadeType.MERGE , CascadeType.PERSIST})
    @JsonIgnore
    private Set<Product> bonusProducts;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_type")
    private BonusCondition conditionType;

    @Column(name = "condition_value")
    private Long conditionValue;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "is_active")
    private boolean isActive;
}
