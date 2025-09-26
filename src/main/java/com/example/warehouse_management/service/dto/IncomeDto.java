package com.example.warehouse_management.service.dto;

import com.example.warehouse_management.entity.enumirated.IncomeStatus;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IncomeDto {
    private Long id;

    private Long productsId;

    private Long wareHouseId;
    private double quantity;

    private Long measureId;
    private Double price;
    private IncomeStatus incomeStatus;
    private Long supplierId;
    private LocalDateTime createdAt;

}
