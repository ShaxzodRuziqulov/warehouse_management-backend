package com.example.warehouse_management.service.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WareHouseDto {
    private Long id;

    private Long productsId;
    private Double quantity;

    private Long measureId;

    private LocalDateTime createdAt;
}
