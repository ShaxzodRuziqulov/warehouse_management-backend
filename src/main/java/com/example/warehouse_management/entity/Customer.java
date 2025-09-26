package com.example.warehouse_management.entity;

import com.example.warehouse_management.entity.enumirated.CustomerStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String customerName;
    private String companyName;
    private String phoneNumber;
    private String address;

    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;



}
