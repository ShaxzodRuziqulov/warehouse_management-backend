package com.example.warehouse_management.repository;

import com.example.warehouse_management.entity.Income;
import com.example.warehouse_management.entity.enumirated.IncomeStatus;
import com.example.warehouse_management.service.dto.IncomeDto;
import com.example.warehouse_management.service.dto.IncomeFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT i FROM Income i WHERE i.incomeStatus = :status")
    List<Income> findByStatus(@Param("status") IncomeStatus status);

    @Query("SELECT COUNT(i) FROM Income i WHERE i.incomeStatus = :status")
    long countByStatus(@Param("status") IncomeStatus status);

    @Query("""
    SELECT new com.example.warehouse_management.service.dto.IncomeFilterDto(
        i.id,
        i.quantity,
        i.incomeStatus,
        i.createdAt,
        p.name,
        w.id,
        m.name,
        s.supplierName
    )
    FROM Income i
        LEFT JOIN i.products p
        LEFT JOIN i.wareHouse w
        LEFT JOIN i.measure m
        LEFT JOIN i.supplier s
""")
    Page<IncomeFilterDto> findAllWithFilter(
            Pageable pageable);

    @Query("""
    SELECT new com.example.warehouse_management.service.dto.IncomeDto(
        i.id,
        p.id,
        w.id,
        i.quantity,
        m.id,
        i.price,
        i.incomeStatus,
        s.id,
        i.createdAt
    )
    FROM Income i
        LEFT JOIN i.products p
        LEFT JOIN i.wareHouse w
        LEFT JOIN i.measure m
        LEFT JOIN i.supplier s
""")
    Page<IncomeDto> findAllIncomes(Pageable pageable);


}
