package com.example.warehouse_management.repository;

import com.example.warehouse_management.entity.Supplier;
import com.example.warehouse_management.entity.enumirated.SupplierStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findBySupplierStatus(SupplierStatus supplierStatus);
}
