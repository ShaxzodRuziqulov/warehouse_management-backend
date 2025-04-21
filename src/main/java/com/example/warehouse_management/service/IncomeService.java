package com.example.warehouse_management.service;

import com.example.warehouse_management.entity.Income;
import com.example.warehouse_management.entity.WareHouse;
import com.example.warehouse_management.repository.IncomeRepository;
import com.example.warehouse_management.repository.WareHouseRepository;
import com.example.warehouse_management.service.dto.IncomeDto;
import com.example.warehouse_management.service.mapper.IncomeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final IncomeMapper incomeMapper;
    private final WareHouseRepository wareHouseRepository;

    public IncomeService(IncomeRepository incomeRepository, IncomeMapper incomeMapper, WareHouseRepository wareHouseRepository) {
        this.incomeRepository = incomeRepository;
        this.incomeMapper = incomeMapper;
        this.wareHouseRepository = wareHouseRepository;
    }


    public IncomeDto create(IncomeDto incomeDto) {
        Income income = incomeMapper.toEntity(incomeDto);


        WareHouse wareHouse = wareHouseRepository.findById(incomeDto.getWareHouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        Double oldQuantity = wareHouse.getQuantity();
        if (oldQuantity == null) {
            oldQuantity = 0.0;
        }

        Double newQuantity = oldQuantity + incomeDto.getQuantity();
        wareHouse.setQuantity(newQuantity);

        wareHouseRepository.save(wareHouse);
        income.setWareHouse(wareHouse);

        incomeRepository.save(income);
        return incomeMapper.toDto(income);
    }

    public IncomeDto update(IncomeDto incomeDto) {
        Income oldIncome = incomeRepository.findById(incomeDto.getId()).orElseThrow(() -> new RuntimeException("Income not found"));
        WareHouse wareHouse = wareHouseRepository.findById(incomeDto.getWareHouseId()).orElseThrow(() -> new RuntimeException("Warehouse not found"));

        double oldQuantity = oldIncome.getQuantity();
        double newQuantity = incomeDto.getQuantity();
        double diffQuantity = oldQuantity - newQuantity;

        Double wareHouseQuantity = wareHouse.getQuantity();
        if (wareHouseQuantity == null) wareHouseQuantity = 0.0;

        wareHouse.setQuantity(wareHouseQuantity + diffQuantity);
        wareHouseRepository.save(wareHouse);

        Income income = incomeMapper.toEntity(incomeDto);
        income.setWareHouse(wareHouse);

        incomeRepository.save(income);
        return incomeMapper.toDto(income);
    }

    public List<IncomeDto> findAll() {
        return incomeRepository
                .findAll()
                .stream()
                .map(incomeMapper::toDto)
                .collect(Collectors.toList());
    }

    public IncomeDto findById(Long id) {
        return incomeMapper
                .toDto(incomeRepository.findById(id)
                        .orElse(null));
    }

}
