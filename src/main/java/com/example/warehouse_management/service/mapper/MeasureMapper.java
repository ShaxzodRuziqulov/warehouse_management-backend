package com.example.warehouse_management.service.mapper;

import com.example.warehouse_management.entity.Measure;
import com.example.warehouse_management.service.dto.MeasureDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MeasureMapper extends EntityMapper<MeasureDto, Measure> {
    MeasureDto toDto(Measure measure);

    Measure toEntity(MeasureDto dto);
}
