package com.example.warehouse_management.entity;

import com.example.warehouse_management.entity.enumirated.Status;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;
}
