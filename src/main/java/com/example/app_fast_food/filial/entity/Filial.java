package com.example.app_fast_food.filial.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "filial")
public class Filial {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @NonNull
    private String name;

    @NonNull
    @NotBlank
    private String destination;

    @Column(name = "open_at")
    private Date openAt;

    @Column(name = "close_at")
    private Date closeAt;

    @NonNull
    @NotBlank
    private Double longitude;

    @NonNull
    @NotBlank
    private Double latitude;

}
