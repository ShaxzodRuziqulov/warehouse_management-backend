package com.example.warehouse_management.service;

import com.example.warehouse_management.entity.Customer;
import com.example.warehouse_management.entity.Order;
import com.example.warehouse_management.entity.WareHouse;
import com.example.warehouse_management.entity.enumirated.OrderStatus;
import com.example.warehouse_management.repository.CategoryRepository;
import com.example.warehouse_management.repository.CustomerRepository;
import com.example.warehouse_management.repository.OrderRepository;
import com.example.warehouse_management.repository.WareHouseRepository;
import com.example.warehouse_management.service.dto.OrderDto;
import com.example.warehouse_management.service.mapper.OrderMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final WareHouseRepository wareHouseRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, WareHouseRepository wareHouseRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.wareHouseRepository = wareHouseRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public OrderDto create(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);

        WareHouse wareHouse = wareHouseRepository.findById(orderDto.getWareHouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        Double oldQuantity = wareHouse.getQuantity();
        if (oldQuantity == null) {
            oldQuantity = 0.0;
        }
        if (oldQuantity >= order.getQuantity()) {
            Double newQuantity = oldQuantity - order.getQuantity();
            wareHouse.setQuantity(newQuantity);

            Customer customer = customerRepository.findById(orderDto.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found"));
            order.setCustomer(customer);
            wareHouseRepository.save(wareHouse);
            order.setWareHouse(wareHouse);
            order.setOrderStatus(OrderStatus.ACTIVE);
            orderRepository.save(order);
            return orderMapper.toDto(order);
        } else {
            throw new RuntimeException("Insufficient stock. Available: " + oldQuantity + ", Requested: " + order.getQuantity());
        }
    }

    @Transactional
    public OrderDto update(OrderDto orderDto) {
        Order oldOrder = orderRepository.findById(orderDto.getId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        WareHouse wareHouse = wareHouseRepository.findById(orderDto.getWareHouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        double oldOrderQuantity = oldOrder.getQuantity();
        double newOrderQuantity = orderDto.getQuantity();
        double quantityDifference = newOrderQuantity - oldOrderQuantity;

        Double wareHouseQuantity = wareHouse.getQuantity();
        if (wareHouseQuantity == null) {
            wareHouseQuantity = 0.0;
        }

        if (quantityDifference > 0) {
            if (wareHouseQuantity < quantityDifference) {
                throw new RuntimeException("Insufficient stock. Available: " + wareHouseQuantity);
            }
            wareHouse.setQuantity(wareHouseQuantity - quantityDifference);
        } else if (quantityDifference < 0) {
            wareHouse.setQuantity(wareHouseQuantity + Math.abs(quantityDifference));
        }

        wareHouseRepository.save(wareHouse);

        oldOrder.setQuantity(newOrderQuantity);
        oldOrder.setWareHouse(wareHouse);
        oldOrder.setOrderStatus(orderDto.getOrderStatus());

        orderRepository.save(oldOrder);

        return orderMapper.toDto(oldOrder);
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

    public long count() {
        return orderRepository.countByStatus(OrderStatus.ACTIVE);
    }

    public List<OrderDto> findByActiveOrder() {
        return orderRepository
                .findByStatus(OrderStatus.ACTIVE)
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Order deleteById(Long id) {
        Order order = orderRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        WareHouse wareHouse = order.getWareHouse();
        if (wareHouse != null) {
            Double oldQuantity = wareHouse.getQuantity();
            if (oldQuantity == null) {
                oldQuantity = 0.0;
            }
            wareHouse.setQuantity(oldQuantity + order.getQuantity());
            wareHouseRepository.save(wareHouse);
        }

        order.setOrderStatus(OrderStatus.DELETED);
        return orderRepository.save(order);
    }

}
