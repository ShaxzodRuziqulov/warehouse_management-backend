package com.example.warehouse_management.service.dto;

import com.example.warehouse_management.entity.enumirated.SupplierStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierDto {
    private Long id;
    private String supplierName;
    private String companyName;
    private String phoneNumber;
    private String address;
    private String email;

    private SupplierStatus supplierStatus;
}
