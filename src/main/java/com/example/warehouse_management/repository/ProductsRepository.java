package com.example.warehouse_management.repository;

import com.example.warehouse_management.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
