package com.example.warehouse_management.service;

import com.example.warehouse_management.entity.Category;
import com.example.warehouse_management.repository.CategoryRepository;
import com.example.warehouse_management.service.dto.CategoryDto;
import com.example.warehouse_management.service.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    public final CategoryRepository categoryRepository;
    public final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryDto create(CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    public CategoryDto update(CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    public CategoryDto get(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElse(null));
    }

    public List<CategoryDto> findAll() {
        List<Category> category = categoryRepository.findAll();
        return category.stream().map(categoryMapper::toDto).collect(Collectors.toList());
    }

    public CategoryDto delete(Long id) {
        Category category =  categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return categoryMapper.toDto(category);
    }
}
