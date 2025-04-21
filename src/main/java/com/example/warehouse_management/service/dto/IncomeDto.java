package com.example.warehouse_management.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncomeDto {
    private Long id;

    private Long productsId;

    private Long wareHouseId;
    private double quantity;

    private Long measureId;
    private Double price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Double wareHouseQuantity;
}
