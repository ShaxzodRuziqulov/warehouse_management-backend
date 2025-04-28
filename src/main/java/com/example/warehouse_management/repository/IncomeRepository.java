package com.example.warehouse_management.repository;

import com.example.warehouse_management.entity.Income;
import com.example.warehouse_management.entity.Order;
import com.example.warehouse_management.entity.enumirated.Status;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    @Query(value = "SELECT i FROM Income i ORDER BY i.createdAt DESC")
    List<Income> findTopNByOrderByCreatedAtDesc(PageRequest pageable);

    default List<Income> findTopNByOrderByCreatedAt(int n) {
        return findTopNByOrderByCreatedAtDesc(PageRequest.of(0, n));
    }
    @Query("SELECT i FROM Income i WHERE i.status = :status")
    List<Income> findByStatus(@Param("status") Status status);
    @Query("SELECT COUNT(i) FROM Income i WHERE i.status = :status")
    long countByStatus(@Param("status") Status status);

}
