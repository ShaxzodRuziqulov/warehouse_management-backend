package com.example.warehouse_management.service.dto;

import com.example.warehouse_management.entity.enumirated.IncomeStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class IncomeFilterDto {
    private Long id;
    private Double quantity;
    private IncomeStatus incomeStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private String productName;
    private Long warehouseId;
    private String measureName;
    private String customerName;

    public IncomeFilterDto(Long id,
                           Double quantity,
                           IncomeStatus incomeStatus,
                           LocalDateTime createdAt,
                           String productName,
                           Long warehouseId,
                           String measureName,
                           String customerName) {
        this.id = id;
        this.quantity = quantity;
        this.incomeStatus = incomeStatus;
        this.createdAt = createdAt;
        this.productName = productName;
        this.warehouseId = warehouseId;
        this.measureName = measureName;
        this.customerName = customerName;
    }
}