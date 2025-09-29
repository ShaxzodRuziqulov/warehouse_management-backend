package com.example.warehouse_management.repository;

import com.example.warehouse_management.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {


    @Query("""
    SELECT c
    FROM Customer c
    WHERE (:customerName IS NULL OR LOWER(c.customerName) LIKE LOWER(CONCAT('%', :customerName, '%')))
      AND (:companyName IS NULL OR LOWER(c.companyName) LIKE LOWER(CONCAT('%', :companyName, '%')))
      AND (:phoneNumber IS NULL OR c.phoneNumber LIKE CONCAT('%', :phoneNumber, '%'))
      AND (:address IS NULL OR LOWER(c.address) LIKE LOWER(CONCAT('%', :address, '%')))
      AND (:customerStatus IS NULL OR c.customerStatus = :customerStatus)
""")
    Page<Customer> findAllWithFilter(
            @Param("customerName") String customerName,
//            @Param("companyName") String companyName,
//            @Param("phoneNumber") String phoneNumber,
//            @Param("address") String address,
//            @Param("customerStatus") CustomerStatus customerStatus,
            Pageable pageable
    );


    @Query("select c from Customer c")
    Page<Customer> findAllCustomers(Pageable pageable);

}
