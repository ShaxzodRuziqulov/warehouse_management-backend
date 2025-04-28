package com.example.warehouse_management.service.mapper;

import com.example.warehouse_management.entity.Order;
import com.example.warehouse_management.entity.Products;
import com.example.warehouse_management.service.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {WareHouseMapper.class, ProductMapper.class, MeasureMapper.class})
public interface OrderMapper extends EntityMapper<OrderDto, Order> {

    @Mapping(source = "products.id", target = "productsId")
    @Mapping(source = "wareHouse.id", target = "wareHouseId")
    @Mapping(source = "measure.id", target = "measureId")
    @Mapping(source = "wareHouse.quantity", target = "wareHouseQuantity", defaultValue = "0.0")
    OrderDto toDto(Order order);

    @Mapping(source = "productsId", target = "products.id")
    @Mapping(source = "wareHouseId", target = "wareHouse.id")
    @Mapping(source = "measureId", target = "measure.id")
    Order toEntity(OrderDto orderDto);

}
