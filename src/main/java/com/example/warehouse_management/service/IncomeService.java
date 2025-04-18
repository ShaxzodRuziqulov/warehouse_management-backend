package com.example.warehouse_management.service;

import com.example.warehouse_management.entity.Income;
import com.example.warehouse_management.repository.IncomeRepository;
import com.example.warehouse_management.service.dto.IncomeDto;
import com.example.warehouse_management.service.mapper.IncomeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final IncomeMapper incomeMapper;

    public IncomeService(IncomeRepository incomeRepository, IncomeMapper incomeMapper) {
        this.incomeRepository = incomeRepository;
        this.incomeMapper = incomeMapper;
    }

    public IncomeDto create(IncomeDto incomeDto) {
        Income income = incomeMapper.toEntity(incomeDto);

        incomeRepository.save(income);
        return incomeMapper.toDto(income);
    }

    public IncomeDto update(IncomeDto incomeDto) {
        Income income = incomeMapper.toEntity(incomeDto);
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
