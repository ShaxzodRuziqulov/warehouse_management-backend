package com.example.warehouse_management.entity;

import com.example.warehouse_management.entity.enumirated.OrderStatus;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
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
    private OrderStatus orderStatus;

    @ManyToOne
    private Customer customer;
}
