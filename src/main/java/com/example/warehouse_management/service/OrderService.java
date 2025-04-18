package com.example.warehouse_management.service;

import com.example.warehouse_management.entity.Order;
import com.example.warehouse_management.repository.OrderRepository;
import com.example.warehouse_management.service.dto.OrderDto;
import com.example.warehouse_management.service.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public OrderDto create(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    public OrderDto update(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
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
