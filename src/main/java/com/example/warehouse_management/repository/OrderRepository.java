package com.example.warehouse_management.repository;

import com.example.warehouse_management.entity.Order;
import com.example.warehouse_management.entity.enumirated.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM orders o WHERE o.orderStatus = :status")
    List<Order> findByStatus(@Param("status") OrderStatus status);

    @Query("SELECT COUNT(o) FROM orders o WHERE o.orderStatus = :status")
    long countByStatus(@Param("status") OrderStatus status);

}
