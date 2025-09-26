package com.example.warehouse_management.service.mapper;

import com.example.warehouse_management.entity.Supplier;
import com.example.warehouse_management.service.dto.SupplierDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper extends EntityMapper<SupplierDto, Supplier> {

    Supplier toEntity(SupplierDto supplierDto);

    SupplierDto toDto(Supplier supplier);
}
