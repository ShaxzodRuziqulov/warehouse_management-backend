package com.example.warehouse_management.repository;

import com.example.warehouse_management.entity.Products;
import com.example.warehouse_management.entity.enumirated.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductsRepository extends JpaRepository<Products, Long> {

    @Query("SELECT p FROM Products p WHERE p.productStatus = :status")
    List<Products> findByStatus(@Param("status") ProductStatus productStatus);

    @Query("SELECT COUNT(p) FROM Products p WHERE p.productStatus = :status")
    long countByStatus(@Param("status") ProductStatus productStatus);

}
