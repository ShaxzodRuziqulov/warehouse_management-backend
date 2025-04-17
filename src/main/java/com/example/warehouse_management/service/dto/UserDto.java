package com.example.warehouse_management.service.dto;

import com.example.warehouse_management.entity.enumirated.Status;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String fullName;
    private String password;
    private String username;
}
