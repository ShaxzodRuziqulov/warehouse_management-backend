package com.example.warehouse_management.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Products products;
    @ManyToOne
    private WareHouse wareHouse;
    private String quantity;
    @ManyToOne
    private Measure measure;
    private Double price;


}
