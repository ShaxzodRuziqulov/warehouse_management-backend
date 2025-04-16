package com.example.warehouse_management.repository;

import com.example.warehouse_management.entity.User;
import com.example.warehouse_management.entity.enumirated.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query("UPDATE Users u SET u.status = :status WHERE u.id = :id")
    User updateStatus(Long id, Status status);

    Optional<User> findByUsername(String username);

}
