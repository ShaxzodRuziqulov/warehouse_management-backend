package com.example.warehouse_management.service.dto;

import com.example.warehouse_management.entity.enumirated.ProductStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private ProductStatus productStatus;
    private Long measureId;
    private Long categoryId;
}
