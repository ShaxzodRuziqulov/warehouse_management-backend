package com.example.warehouse_management.service.dto;


import com.example.warehouse_management.entity.enumirated.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private Long productsId;
    private Long wareHouseId;
    private Double quantity;
    private Long measureId;
    private OrderStatus orderStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Double wareHouseQuantity;

}
