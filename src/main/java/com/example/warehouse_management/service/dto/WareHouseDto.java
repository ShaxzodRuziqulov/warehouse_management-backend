package com.example.warehouse_management.service.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WareHouseDto {
    private Long id;

    private Long productsId;
    private Double quantity;

    private Long measureId;

    private LocalDateTime createdAt;
}
