package com.example.warehouse_management.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class RoleDTO {

    private Long id;

    private String name;

    private String description;

}