package com.example.warehouse_management.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateRequest {
    private ProductDto productDto;
    private WareHouseDto wareHouseDto;
}
