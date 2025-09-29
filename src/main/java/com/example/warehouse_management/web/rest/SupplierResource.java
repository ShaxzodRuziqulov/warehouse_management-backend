package com.example.warehouse_management.web.rest;

import com.example.warehouse_management.service.SupplierService;
import com.example.warehouse_management.service.dto.SupplierDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin/supplier")
public class SupplierResource {
    private final SupplierService supplierService;


    public SupplierResource(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody SupplierDto supplierDto) {
        return ResponseEntity.ok(supplierService.create(supplierDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody SupplierDto supplierDto, @PathVariable Long id) {
        if (!supplierDto.getId().equals(id) && supplierDto.getId() != 0) {
            return ResponseEntity.badRequest().body("Invalid id");
        }
        return ResponseEntity.ok(supplierService.update(supplierDto));
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllSupplier() {
        return ResponseEntity.ok(supplierService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.delete(id));
    }

    @GetMapping("/get-status-type")
    public ResponseEntity<?> findAllSuppliersStatus(@RequestParam(defaultValue = "ACTIVE") String type) {
        List<SupplierDto> result = supplierService.findByStatus(type);
        return ResponseEntity.ok(result);
    }
}
