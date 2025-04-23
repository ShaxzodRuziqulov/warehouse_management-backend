package com.example.warehouse_management.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IncomeDto {
    private Long id;

    private Long productsId;

    private Long wareHouseId;
    private double quantity;

    private Long measureId;
    private Double price;

    private LocalDateTime createdAt;


}
