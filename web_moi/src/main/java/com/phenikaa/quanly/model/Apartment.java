package com.phenikaa.quanly.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "apartment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String block;

    @Column(nullable = false)
    private int floor;

    @Column(nullable = false)
    private String number;

    private double area;
}
