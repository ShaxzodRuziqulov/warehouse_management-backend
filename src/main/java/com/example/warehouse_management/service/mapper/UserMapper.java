package com.example.warehouse_management.service.mapper;

import com.example.warehouse_management.entity.User;
import com.example.warehouse_management.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {
    UserDto toDto(User user);

    @Mapping(source = "username", target = "username")
    User toEntity(UserDto userDto);

}
