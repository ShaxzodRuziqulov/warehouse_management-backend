package com.example.warehouse_management.repository;

import com.example.warehouse_management.entity.Products;
import com.example.warehouse_management.entity.enumirated.Status;
import com.example.warehouse_management.service.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductsRepository extends JpaRepository<Products, Long> {

    @Query("SELECT p FROM Products p WHERE p.status = :status")
    List<Products> findByStatus(@Param("status") Status status);

}
