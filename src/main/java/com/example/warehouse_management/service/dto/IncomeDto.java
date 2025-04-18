package com.example.warehouse_management.service.dto;

import lombok.Data;

@Data
public class IncomeDto {
    private Long id;

    private Long productsId;

    private Long wareHouseId;
    private double quantity;

    private Long measureId;
    private Double price;
}
