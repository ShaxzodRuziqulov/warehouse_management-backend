package com.example.warehouse_management.service.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long id;
    private Long productsId;
    private Long wareHouseId;
    private Double quantity;
    private Long measureId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Double wareHouseQuantity;

}
