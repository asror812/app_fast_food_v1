package com.example.app_fast_food.discount;

import com.example.app_fast_food.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "discount")
@SQLRestriction("is_active=true")
@SQLDelete(sql = ("update discount set is_active=true where id=?"))
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String  name;
    private Integer percentage;
    private LocalDateTime endDate;

    @Column(name = "required_quantity")
    private Integer requiredQuantity;

    private boolean isActive ;
    @ManyToMany(mappedBy = "discounts")
     private Set<Product> products;
}
