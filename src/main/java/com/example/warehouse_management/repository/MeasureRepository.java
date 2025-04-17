package com.example.warehouse_management.repository;

import com.example.warehouse_management.entity.Measure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasureRepository extends JpaRepository<Measure, Long> {
}
