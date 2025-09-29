package com.example.warehouse_management.service;

import com.example.warehouse_management.entity.Supplier;
import com.example.warehouse_management.entity.enumirated.SupplierStatus;
import com.example.warehouse_management.repository.SupplierRepository;
import com.example.warehouse_management.service.dto.SupplierDto;
import com.example.warehouse_management.service.mapper.SupplierMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierService(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    public SupplierDto create(SupplierDto supplierDto) {
        Supplier supplier = supplierMapper.toEntity(supplierDto);
        supplier.setSupplierStatus(SupplierStatus.ACTIVE);
        return supplierMapper.toDto(supplierRepository.save(supplier));
    }

    public SupplierDto update(SupplierDto supplierDto) {
        Supplier supplier = supplierMapper.toEntity(supplierDto);
        return supplierMapper.toDto(supplierRepository.save(supplier));
    }

    public List<SupplierDto> findAll() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream().map(supplierMapper::toDto).collect(Collectors.toList());
    }

    public SupplierDto get(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found"));
        return supplierMapper.toDto(supplier);
    }

    public SupplierDto delete(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplier.setSupplierStatus(SupplierStatus.DELETED);
        Supplier update = supplierRepository.save(supplier);
        return supplierMapper.toDto(update);
    }

    public SupplierDto deleted(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found"));
        SupplierDto supplierDto = supplierMapper.toDto(supplier);
        supplierRepository.delete(supplier);
        return supplierDto;
    }

    public List<SupplierDto> findByStatus(String type) {
        List<Supplier> itemList = switch (type.toUpperCase()) {
            case "ACTIVE" -> supplierRepository.findBySupplierStatus(SupplierStatus.ACTIVE);
            case "INACTIVE" -> supplierRepository.findBySupplierStatus(SupplierStatus.INACTIVE);
            default -> supplierRepository.findBySupplierStatus(SupplierStatus.DELETED);
        };
        return itemList.stream().map(supplierMapper::toDto).collect(Collectors.toList());
    }
}
