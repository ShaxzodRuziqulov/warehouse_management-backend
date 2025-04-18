package com.example.warehouse_management.service.mapper;

import com.example.warehouse_management.entity.WareHouse;
import com.example.warehouse_management.service.dto.WareHouseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, MeasureMapper.class})
public interface WareHouseMapper extends EntityMapper<WareHouseDto, WareHouse> {

    @Mapping(source = "products.id", target = "productsId")
    @Mapping(source = "measure.id", target = "measureId")
    WareHouseDto toDto(WareHouse wareHouse);

    @Mapping(source = "productsId", target = "products.id")
    @Mapping(source = "measureId", target = "measure.id")
    WareHouse toEntity(WareHouseDto wareHouseDto);
}
