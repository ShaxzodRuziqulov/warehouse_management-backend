package com.example.warehouse_management.web.rest;

import com.example.warehouse_management.entity.Order;
import com.example.warehouse_management.service.OrderService;
import com.example.warehouse_management.service.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderResource {
    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderDto orderDto) throws URISyntaxException {
        OrderDto result = orderService.create(orderDto);
        return ResponseEntity.created(new URI("/api/order/create")).body(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody OrderDto orderDto, @PathVariable Long id) throws URISyntaxException {
        if (!orderDto.getId().equals(id) || orderDto.getId() == 0) {
            return ResponseEntity.badRequest().body("invalid id");
        }

        OrderDto result = orderService.update(orderDto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        List<OrderDto> result = orderService.findAll();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        OrderDto result = orderService.findById(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/count")
    public ResponseEntity<?> count() {
        long result = orderService.count();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/allActive")
    public ResponseEntity<?> findAllActive() {
        List<OrderDto> orderDto = orderService.findByActiveOrder();
        return ResponseEntity.ok().body(orderDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Order order = orderService.deleteById(id);
        return ResponseEntity.ok().body(order);
    }
}
