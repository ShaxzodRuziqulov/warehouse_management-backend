package com.example.warehouse_management.service;

import com.example.warehouse_management.entity.Category;
import com.example.warehouse_management.repository.CategoryRepository;
import com.example.warehouse_management.repository.ProductsRepository;
import com.example.warehouse_management.service.dto.CategoryDto;
import com.example.warehouse_management.service.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    public final CategoryRepository categoryRepository;
    public final CategoryMapper categoryMapper;
    private final ProductsRepository productsRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, ProductsRepository productsRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.productsRepository = productsRepository;
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

    public CategoryDto deleted(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        boolean hasProducts = productsRepository.existsByCategoryId(id);
        if (hasProducts) {
            throw new RuntimeException("Cannot delete category with products");
        }
        categoryRepository.delete(category);
        return categoryMapper.toDto(category);
    }
}
