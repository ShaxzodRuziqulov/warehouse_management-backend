package com.example.warehouse_management.repository;

import com.example.warehouse_management.entity.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WareHouseRepository extends JpaRepository<WareHouse, Long> {
    Optional<WareHouse> findByProductsIdAndMeasureId(Long productsId, Long measureId);


}
