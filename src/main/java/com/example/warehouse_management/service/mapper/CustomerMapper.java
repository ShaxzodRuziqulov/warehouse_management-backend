package com.example.warehouse_management.service.mapper;

import com.example.warehouse_management.entity.Customer;
import com.example.warehouse_management.service.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerDto customerDto);

    CustomerDto toDto(Customer customer);
}
