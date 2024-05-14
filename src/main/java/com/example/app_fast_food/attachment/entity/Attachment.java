package com.example.app_fast_food.attachment.entity;


import com.example.app_fast_food.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "attachment")
public class Attachment {


    @Id
    private UUID id;

    private String originalName;

    @NotNull
    @NotBlank
    private String downloadUrl;

    private String type;

    private Long size;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private boolean isMain;


}