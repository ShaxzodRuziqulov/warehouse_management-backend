package com.example.warehouse_management.service;

import com.example.warehouse_management.entity.Category;
import com.example.warehouse_management.entity.Measure;
import com.example.warehouse_management.entity.Products;
import com.example.warehouse_management.entity.WareHouse;
import com.example.warehouse_management.entity.enumirated.ProductStatus;
import com.example.warehouse_management.repository.CategoryRepository;
import com.example.warehouse_management.repository.MeasureRepository;
import com.example.warehouse_management.repository.ProductsRepository;
import com.example.warehouse_management.repository.WareHouseRepository;
import com.example.warehouse_management.service.dto.ProductDto;
import com.example.warehouse_management.service.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductsRepository productsRepository;
    private final MeasureRepository measureRepository;
    private final WareHouseRepository wareHouseRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductMapper productMapper, ProductsRepository productsRepository, MeasureRepository measureRepository, WareHouseRepository wareHouseRepository, CategoryRepository categoryRepository) {
        this.productMapper = productMapper;
        this.productsRepository = productsRepository;
        this.measureRepository = measureRepository;
        this.wareHouseRepository = wareHouseRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductDto create(ProductDto productDto) {
        Products result = productMapper.toEntity(productDto);

        result.setProductStatus(ProductStatus.ACTIVE);

        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        result.setCategory(category);

        result = productsRepository.save(result);
//        Measure measure = measureRepository.findById(productDto.getMeasureId())
//                .orElseThrow(() -> new RuntimeException("Measure not found"));


        WareHouse wareHouse = new WareHouse();
        wareHouse.setProducts(result);
        wareHouse.setQuantity(0.0);
//        wareHouse.setMeasure(measure);
        wareHouseRepository.save(wareHouse);

        return productMapper.toDto(result);
    }

    public ProductDto update(ProductDto productDto) {
        Products result = productMapper.toEntity(productDto);
        productsRepository.save(result);
        return productMapper.toDto(result);
    }

    public List<ProductDto> findAll() {
        return productsRepository
                .findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public Products findById(Long id) {
        return productsRepository.findById(id).orElseGet(Products::new);
    }


    public Products deleteById(Long id) {
        Products products = productsRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        products.setProductStatus(ProductStatus.DELETED);
        return productsRepository.save(products);
    }

    public long count() {
        return productsRepository.countByStatus(ProductStatus.ACTIVE);
    }

    public Map<String, Long> getProductChartData() {
        List<Products> allProducts = productsRepository.findAll();

        return allProducts.stream()
                .collect(Collectors.groupingBy(
                        product -> product.getProductStatus().name(),
                        Collectors.counting()
                ));
    }

    public List<ProductDto> findActiveProducts() {
        return productsRepository.findByStatus(ProductStatus.ACTIVE)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> findDeleteProducts() {
        return productsRepository.findByStatus(ProductStatus.DELETED)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
