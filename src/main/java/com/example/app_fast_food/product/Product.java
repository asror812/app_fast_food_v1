package com.example.app_fast_food.product;


import com.example.app_fast_food.attachment.entity.Attachment;
import com.example.app_fast_food.bonus.Bonus;
import com.example.app_fast_food.category.entity.Category;
import com.example.app_fast_food.comment.Comment;
import com.example.app_fast_food.discount.Discount;
import com.example.app_fast_food.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
    @JoinColumn(name = "category_id" , nullable = false)
    private Category category;

    private Integer weight;

    private Long sold;


    @ManyToMany
    @JoinTable(
            name = "product_discounts",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "discount_id")
    )
    private Set<Discount> discounts;

    @ManyToMany
    @JoinTable(
            name = "product_bonuses" ,
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "bonus_id")
    )
    private Set<Bonus> bonuses;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment main;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment other;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Set<Discount> getActiveDiscounts(){
        return discounts.stream()
                .filter(Discount::isActive)
                .collect(Collectors.toSet());
    }
}
