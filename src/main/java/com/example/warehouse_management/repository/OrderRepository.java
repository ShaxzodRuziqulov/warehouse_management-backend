package com.example.warehouse_management.repository;

import com.example.warehouse_management.entity.Order;
import com.example.warehouse_management.entity.Products;
import com.example.warehouse_management.entity.enumirated.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM orders o WHERE o.status = :status")
    List<Order> findByStatus(@Param("status") Status status);
    @Query("SELECT COUNT(o) FROM orders o WHERE o.status = :status")
    long countByStatus(@Param("status") Status status);

}
