package com.example.warehouse_management.entity;

import com.example.warehouse_management.entity.enumirated.SupplierStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Supplier extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String supplierName;
    private String companyName;
    private String phoneNumber;
    private String address;
    private String email;

    @Enumerated(EnumType.STRING)
    private SupplierStatus supplierStatus;


}
