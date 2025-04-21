package com.example.warehouse_management.service;

import com.example.warehouse_management.entity.Measure;
import com.example.warehouse_management.entity.Products;
import com.example.warehouse_management.entity.WareHouse;
import com.example.warehouse_management.entity.enumirated.Status;
import com.example.warehouse_management.repository.MeasureRepository;
import com.example.warehouse_management.repository.ProductsRepository;
import com.example.warehouse_management.repository.WareHouseRepository;
import com.example.warehouse_management.service.dto.ProductDto;
import com.example.warehouse_management.service.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductsRepository productsRepository;
    private final MeasureRepository measureRepository;
    private final WareHouseRepository wareHouseRepository;


    public ProductService(ProductMapper productMapper, ProductsRepository productsRepository, MeasureRepository measureRepository, WareHouseRepository wareHouseRepository ) {
        this.productMapper = productMapper;
        this.productsRepository = productsRepository;
        this.measureRepository = measureRepository;
        this.wareHouseRepository = wareHouseRepository;

    }

    public ProductDto create(ProductDto productDto) {
        Products result = productMapper.toEntity(productDto);

        result.setStatus(Status.ACTIVE);

        result = productsRepository.save(result);
        Measure measure = measureRepository.findById(productDto.getMeasureId())
                .orElseThrow(() -> new RuntimeException("Measure not found"));

        WareHouse wareHouse = new WareHouse();
        wareHouse.setProducts(result);
        wareHouse.setQuantity(0.0);
        wareHouse.setMeasure(measure);
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
        products.setStatus(Status.DELETED);
        return productsRepository.save(products);
    }
}
