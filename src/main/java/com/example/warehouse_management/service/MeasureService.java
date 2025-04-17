package com.example.warehouse_management.service;

import com.example.warehouse_management.entity.Measure;
import com.example.warehouse_management.repository.MeasureRepository;
import com.example.warehouse_management.service.dto.MeasureDto;
import com.example.warehouse_management.service.mapper.MeasureMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasureService {
    private final MeasureRepository measureRepository;
    private final MeasureMapper measureMapper;

    public MeasureService(MeasureRepository measureRepository, MeasureMapper measureMapper) {
        this.measureRepository = measureRepository;
        this.measureMapper = measureMapper;
    }

    public MeasureDto create(MeasureDto measureDto) {
        Measure measure = measureMapper.toEntity(measureDto);
        measure = measureRepository.save(measure);
        return measureMapper.toDto(measure);
    }

    public MeasureDto update(MeasureDto measureDto) {
        Measure measure = measureMapper.toEntity(measureDto);
        measure = measureRepository.save(measure);
        return measureMapper.toDto(measure);
    }

    public List<MeasureDto> findAll() {
        return measureRepository
                .findAll()
                .stream()
                .map(measureMapper::toDto)
                .collect(Collectors
                        .toList());
    }

    public Measure findById(Long id) {
        return measureRepository
                .findById(id)
                .orElseGet(Measure::new);
    }


}
