package com.example.warehouse_management.web.rest;

import com.example.warehouse_management.entity.Customer;
import com.example.warehouse_management.service.CustomerService;
import com.example.warehouse_management.service.dto.CustomerDto;
import com.example.warehouse_management.service.request.CustomerFilterRequest;
import com.example.warehouse_management.service.request.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/customer")
public class CustomerResource {
    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.create(customerDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        if (!customerDto.getId().equals(id) && customerDto.getId() != 0) {
            return ResponseEntity.badRequest().body("Invalid id");
        }
        return ResponseEntity.ok(customerService.update(customerDto));
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> getAllCustomer() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.delete(id));
    }

    @PostMapping("/paging")
    public ResponseEntity<?> findAllWithFilter(@RequestBody CustomerFilterRequest filterRequest, @PageableDefault Pageable pageable) {
        Page<Customer> result = customerService.findAllWithFilter(filterRequest, pageable);
        return ResponseEntity.ok(new PageResponse<>(result));
    }
}
