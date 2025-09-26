package com.example.warehouse_management.service.mapper;


import com.example.warehouse_management.entity.Role;
import com.example.warehouse_management.service.dto.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface RoleMapper {

    RoleDTO toDto(Role role);

    Role toEntity(RoleDTO roleDTO);
}
