package com.example.warehouse_management.service;

import com.example.warehouse_management.entity.WareHouse;
import com.example.warehouse_management.repository.WareHouseRepository;
import com.example.warehouse_management.service.dto.WareHouseDto;
import com.example.warehouse_management.service.mapper.WareHouseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WareHouseService {
    private final WareHouseRepository wareHouseRepository;
    private final WareHouseMapper wareHouseMapper;

    public WareHouseService(WareHouseRepository wareHouseRepository, WareHouseMapper wareHouseMapper) {
        this.wareHouseRepository = wareHouseRepository;
        this.wareHouseMapper = wareHouseMapper;
    }

    public WareHouseDto create(WareHouseDto wareHouseDto) {
        WareHouse wareHouse = wareHouseMapper.toEntity(wareHouseDto);
        wareHouseRepository.save(wareHouse);
        return wareHouseMapper.toDto(wareHouse);
    }

    public WareHouseDto update(WareHouseDto wareHouseDto) {
        WareHouse wareHouse = wareHouseMapper.toEntity(wareHouseDto);
        wareHouseRepository.save(wareHouse);
        return wareHouseMapper.toDto(wareHouse);
    }

    public List<WareHouseDto> findAll() {
        return wareHouseRepository
                .findAll()
                .stream()
                .map(wareHouseMapper::toDto)
                .collect(Collectors.toList());
    }

    public WareHouseDto findById(Long id) {
        return wareHouseMapper.toDto(wareHouseRepository.findById(id).orElse(null));
    }
}
