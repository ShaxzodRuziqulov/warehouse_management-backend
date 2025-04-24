package com.example.warehouse_management.service.mapper;

import com.example.warehouse_management.entity.Products;
import com.example.warehouse_management.service.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDto, Products> {
    @Mapping(source = "status", target = "status")
    ProductDto toDto(Products products);

    @Mapping(source = "status", target = "status")
    Products toEntity(ProductDto productDto);
}
