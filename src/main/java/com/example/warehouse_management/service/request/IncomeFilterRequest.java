package com.example.warehouse_management.service.request;

import com.example.warehouse_management.entity.enumirated.IncomeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncomeFilterRequest {
    private double price;
    private double quantity;
    private IncomeStatus incomeStatus;
}
