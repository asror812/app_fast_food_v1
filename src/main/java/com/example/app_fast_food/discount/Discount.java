package com.example.app_fast_food.discount;

import com.example.app_fast_food.product.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String  name;
    private Double percentage;
    private LocalDate until;

    @ManyToMany(mappedBy = "discounts")
     private List<Product> products;
}
