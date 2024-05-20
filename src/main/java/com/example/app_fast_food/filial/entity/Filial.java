package com.example.app_fast_food.filial.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @NotNull
    private String name;

    @NotNull
    @NotBlank
    private String destination;

    @Column(name = "open_at")
    private Date openAt;

    @Column(name = "close_at")
    private Date closeAt;

    @NotNull
    @NotBlank
    private Double longitude;

    @Enumerated(EnumType.STRING)
    private Region region;

    @NotNull
    @NotBlank
    private Double latitude;

}
