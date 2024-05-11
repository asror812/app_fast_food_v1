package com.example.app_fast_food.product.entity;


import com.example.app_fast_food.attachment.entity.Attachment;
import com.example.app_fast_food.bonus.Bonus;
import com.example.app_fast_food.category.entity.Category;
import com.example.app_fast_food.comment.Comment;
import com.example.app_fast_food.discount.Discount;
import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "product")
    private List<Bonus> bonuses;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Attachment> attachment;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;
}
