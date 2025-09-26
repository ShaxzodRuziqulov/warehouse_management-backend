package com.example.warehouse_management.entity;

import com.example.warehouse_management.entity.enumirated.IncomeStatus;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.function.Suppliers;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class Income extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Products products;
    @ManyToOne
    private WareHouse wareHouse;
    private double quantity;
    @ManyToOne
    private Measure measure;
    private Double price;
    @Enumerated(EnumType.STRING)
    private IncomeStatus incomeStatus;

    @ManyToOne
    private Supplier supplier;
}
