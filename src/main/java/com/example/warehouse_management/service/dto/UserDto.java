package com.example.warehouse_management.service.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    private String username;
}
