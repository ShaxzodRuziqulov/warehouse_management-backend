package com.example.warehouse_management.service.dto;

import com.example.warehouse_management.entity.enumirated.Status;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Status status;
}
