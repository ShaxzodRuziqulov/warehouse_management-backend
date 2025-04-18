package com.example.warehouse_management.entity;

import com.example.warehouse_management.entity.enumirated.Status;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Measure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String piece;
    private String liter;
    private Double kilogram;
}
