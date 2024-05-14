package com.example.app_fast_food.category.entity;


import com.example.app_fast_food.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Entity
@Table(name = "category")
public class Category {

    @Id
    private String name;

    private String subCategory;

    @OneToMany(mappedBy = "category" )
    private List<Product> products;
}
