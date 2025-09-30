package com.example.warehouse_management.service.mapper;

import com.example.warehouse_management.entity.Income;
import com.example.warehouse_management.service.dto.IncomeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, WareHouseMapper.class, MeasureMapper.class})
public interface IncomeMapper extends EntityMapper<IncomeDto, Income> {
    @Mapping(source = "products.id", target = "productsId")
    @Mapping(source = "wareHouse.id", target = "wareHouseId")
    @Mapping(source = "measure.id", target = "measureId")
    @Mapping(source = "supplier.id",target = "supplierId")
    IncomeDto toDto(Income income);

    @Mapping(source = "measureId", target = "measure.id")
    @Mapping(source = "wareHouseId", target = "wareHouse.id")
    @Mapping(source = "productsId", target = "products.id")
    Income toEntity(IncomeDto incomeDto);
}
