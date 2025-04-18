package com.example.warehouse_management.service.mapper;

import com.example.warehouse_management.entity.Products;
import com.example.warehouse_management.service.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDto, Products> {

    ProductDto toDto(Products products);

    Products toEntity(ProductDto productDto);
}
