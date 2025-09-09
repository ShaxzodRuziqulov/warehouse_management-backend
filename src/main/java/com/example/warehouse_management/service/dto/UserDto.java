package com.example.warehouse_management.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    private String username;
}
