package com.example.warehouse_management.service.dto;

import lombok.Data;

@Data
public class MeasureDto {
    private Long id;
    private String name;
    private String piece;
    private String liter;
    private Double kilogram;
}
