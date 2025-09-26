package com.example.warehouse_management.service.mapper;

import com.example.warehouse_management.entity.Category;
import com.example.warehouse_management.service.dto.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDto, Category> {

    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDto);
}
