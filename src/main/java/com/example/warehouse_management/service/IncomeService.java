package com.example.warehouse_management.service;

import com.example.warehouse_management.entity.Income;
import com.example.warehouse_management.entity.WareHouse;
import com.example.warehouse_management.entity.enumirated.IncomeStatus;
import com.example.warehouse_management.repository.IncomeRepository;
import com.example.warehouse_management.repository.WareHouseRepository;
import com.example.warehouse_management.service.dto.IncomeDto;
import com.example.warehouse_management.service.mapper.IncomeMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public IncomeDto create(IncomeDto incomeDto) {
        Income income = incomeMapper.toEntity(incomeDto);
        income.setIncomeStatus(IncomeStatus.ACTIVE);

        WareHouse wareHouse = wareHouseRepository.findByProductsIdAndMeasureId(
                incomeDto.getProductsId(), incomeDto.getMeasureId()
        ).orElseThrow(() -> new EntityNotFoundException("Product with this measure not found in warehouse"));

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

    @Transactional
    public IncomeDto update(Long id, IncomeDto incomeDto) {
        Income oldIncome = incomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Income not found"));

        WareHouse wareHouse = wareHouseRepository.findByProductsIdAndMeasureId(
                oldIncome.getWareHouse().getProducts().getId(),
                oldIncome.getWareHouse().getMeasure().getId()
        ).orElseThrow(() -> new RuntimeException("Product with this measure not found in warehouse"));

        Double oldQuantity = wareHouse.getQuantity();
        if (oldQuantity == null) {
            oldQuantity = 0.0;
        }

        double afterRemovingOldIncome = oldQuantity - oldIncome.getQuantity();
        if (afterRemovingOldIncome < 0) {
            afterRemovingOldIncome = 0.0;
        }
        wareHouse.setQuantity(afterRemovingOldIncome);

        Double newQuantity = wareHouse.getQuantity() + incomeDto.getQuantity();
        wareHouse.setQuantity(newQuantity);

        wareHouseRepository.save(wareHouse);

        oldIncome.setQuantity(incomeDto.getQuantity());
        oldIncome.setPrice(incomeDto.getPrice());
        oldIncome.setIncomeStatus(incomeDto.getIncomeStatus());
        oldIncome.setCreatedAt(incomeDto.getCreatedAt());

        incomeRepository.save(oldIncome);

        return incomeMapper.toDto(oldIncome);
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

    public List<IncomeDto> findByActiveIncome() {
        return incomeRepository
                .findByStatus(IncomeStatus.ACTIVE)
                .stream()
                .map(incomeMapper::toDto)
                .collect(Collectors.toList());
    }

    public long count() {
        return incomeRepository.countByStatus(IncomeStatus.ACTIVE);
    }

    public List<IncomeDto> getLatestIncomes(int limit) {
        return incomeRepository.findTopNByOrderByCreatedAt(limit).stream()
                .map(income -> new IncomeDto(
                        income.getId(),
                        income.getProducts().getId(),
                        income.getWareHouse().getId(),
                        income.getQuantity(),
                        income.getMeasure().getId(),
                        income.getPrice(),
                        income.getIncomeStatus(),
                        income.getCreatedAt()
                ))

                .collect(Collectors.toList());
    }

    @Transactional
    public Income delete(Long id) {
        Income income = incomeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Income not found"));

        WareHouse wareHouse = income.getWareHouse();
        if (wareHouse != null) {
            Double currentQuantity = wareHouse.getQuantity();
            if (currentQuantity == null) {
                currentQuantity = 0.0;
            }

            if (currentQuantity < income.getQuantity()) {
                throw new RuntimeException("Not enough stock to delete this income. Available: "
                        + currentQuantity + ", Required: " + income.getQuantity());
            }

            wareHouse.setQuantity(currentQuantity - income.getQuantity());
            wareHouseRepository.save(wareHouse);
        }

        income.setIncomeStatus(IncomeStatus.DELETED);
        return incomeRepository.save(income);
    }

}
