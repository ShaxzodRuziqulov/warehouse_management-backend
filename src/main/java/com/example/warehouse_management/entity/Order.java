package com.example.warehouse_management.entity;

import com.example.warehouse_management.entity.enumirated.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Products products;
    @ManyToOne
    private WareHouse wareHouse;
    private Double quantity;
    @ManyToOne
    private Measure measure;
    @Enumerated(EnumType.STRING)
    private Status status;

}
