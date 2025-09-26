package com.example.warehouse_management.service.dto;

import com.example.warehouse_management.entity.enumirated.CustomerStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private Long id;
    private String customerName;
    private String companyName;
    private String phoneNumber;
    private String address;

    private CustomerStatus customerStatus;
}
