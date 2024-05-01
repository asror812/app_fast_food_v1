package com.example.app_fast_food.product.entity;


import com.example.app_fast_food.category.entity.Category;
import com.example.app_fast_food.discount.Discount;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull @NotBlank
    private String name;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "bonus_product_id")
    private Product product_id;

    @Column(name = "required_quantity")
    private Integer requiredQuantity;

    @NotNull @NotBlank
    private String categoryName;

    private Integer weight;

    private Long sold;


    @ManyToMany
    @JoinTable(
            name = "product_discount",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "discount_id")
    )
    private List<Discount> discounts;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
