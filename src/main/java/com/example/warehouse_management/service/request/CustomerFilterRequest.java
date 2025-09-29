package com.example.warehouse_management.service.request;

import com.example.warehouse_management.entity.enumirated.CustomerStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerFilterRequest {
    private Long id;
    private String customerName;
    private String companyName;
    private String phoneNumber;
    private String address;
    private CustomerStatus customerStatus;

}
