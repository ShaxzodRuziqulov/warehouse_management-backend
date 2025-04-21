package com.example.warehouse_management.service.dto;

import lombok.Data;

@Data
public class ProductCreateRequest {
    private ProductDto productDto;
    private WareHouseDto wareHouseDto;
}
