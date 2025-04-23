package com.example.warehouse_management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Measure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
