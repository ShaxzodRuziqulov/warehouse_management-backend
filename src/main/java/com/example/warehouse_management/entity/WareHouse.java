package com.example.warehouse_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class WareHouse {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Products products;
    private String quantity;

    @ManyToOne
    private Measure measure;
    private LocalDateTime updateAt;

}
