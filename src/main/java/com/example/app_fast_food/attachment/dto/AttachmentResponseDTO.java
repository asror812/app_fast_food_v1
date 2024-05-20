package com.example.app_fast_food.attachment.dto;

import com.example.app_fast_food.product.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class AttachmentResponseDTO {

    @NotNull @NotBlank
    private String downloadUrl;

    @NotNull @NotBlank
    private Long size;

}
