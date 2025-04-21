package com.example.warehouse_management.service;

import com.example.warehouse_management.entity.Order;
import com.example.warehouse_management.entity.WareHouse;
import com.example.warehouse_management.repository.OrderRepository;
import com.example.warehouse_management.repository.WareHouseRepository;
import com.example.warehouse_management.service.dto.OrderDto;
import com.example.warehouse_management.service.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final WareHouseRepository wareHouseRepository;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, WareHouseRepository wareHouseRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.wareHouseRepository = wareHouseRepository;
    }

    public OrderDto create(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);

        WareHouse wareHouse = wareHouseRepository.findById(orderDto.getWareHouseId()).orElseThrow(() -> new RuntimeException("Warehouse not found"));
        Double oldQuantity = wareHouse.getQuantity();
        if (oldQuantity == null) {
            oldQuantity = 0.0;
        }
        if (oldQuantity > order.getQuantity()) {
            Double newQuantity = oldQuantity - orderDto.getQuantity();
            wareHouse.setQuantity(newQuantity);
            wareHouseRepository.save(wareHouse);
            orderRepository.save(order);
            return orderMapper.toDto(order);
        } else {
            throw new RuntimeException("Order already exists. Mavjud: " + oldQuantity + " Siz kiritgan: " + orderDto.getQuantity());
        }
    }

    public OrderDto update(OrderDto orderDto) {
        Order oldOrder = orderRepository.findById(orderDto.getId()).orElseThrow(() -> new RuntimeException("Order not found"));
        WareHouse wareHouse = wareHouseRepository.findById(orderDto.getWareHouseId()).orElseThrow(() -> new RuntimeException("Warehouse not found"));

        double oldQuantity = oldOrder.getQuantity();
        double newQuantity = orderDto.getQuantity();
        double difference = oldQuantity - newQuantity;

        Double currentQuantity = wareHouse.getQuantity();

        if (currentQuantity == null) currentQuantity = 0.0;
        double updatedQuantity = currentQuantity + difference;
        if (updatedQuantity < 0) {
            throw new RuntimeException("Order already exists. Mavjud: " + oldQuantity);
        }

        wareHouse.setQuantity(newQuantity);
        wareHouseRepository.save(wareHouse);

        Order order = orderMapper.toEntity(orderDto);
        order.setQuantity(updatedQuantity);
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    public List<OrderDto> findAll() {
        return orderRepository
                .findAll()
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    public OrderDto findById(Long id) {
        return orderRepository.findById(id).map(orderMapper::toDto).orElse(null);
    }
}
