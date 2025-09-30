package com.example.warehouse_management.repository;

import com.example.warehouse_management.entity.Order;
import com.example.warehouse_management.entity.enumirated.OrderStatus;
import com.example.warehouse_management.service.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM orders o WHERE o.orderStatus = :status")
    List<Order> findByStatus(@Param("status") OrderStatus status);

    @Query("SELECT COUNT(o) FROM orders o WHERE o.orderStatus = :status")
    long countByStatus(@Param("status") OrderStatus status);

    @Query("""
            SELECT (o.id,o.quantity,o.orderStatus,o.createdAt,
                    p.name,w.id,m.name,c.customerName
            )
            FROM orders o
                LEFT JOIN o.products p
                LEFT JOIN o.wareHouse w
                LEFT JOIN o.measure m
                LEFT JOIN o.customer c
            where (:quantity IS NULL OR o.quantity = :quantity)
              AND (:orderStatus IS NULL OR o.orderStatus = :orderStatus)
              AND (:createdAt IS NULL OR o.createdAt = :createdAt)
              AND (:productName IS NULL OR p.name = :productName)
              AND (:measureName IS NULL OR m.name = :measureName)
              AND (:customerName IS NULL OR c.customerName = :customerName)
            """)
    Page<OrderDto> findAllWithFilter(
            @Param("quantity") Double quantity,
            @Param("orderStatus") OrderStatus orderStatus,
            @Param("createdAt") Date createdAt,
            @Param("productName") String productName,
            @Param("measureName") String measureName,
            @Param("customerName") String customerName,
            Pageable pageable);
}
