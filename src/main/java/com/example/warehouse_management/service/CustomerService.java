package com.example.warehouse_management.service;

import com.example.warehouse_management.entity.Customer;
import com.example.warehouse_management.entity.enumirated.CustomerStatus;
import com.example.warehouse_management.repository.CustomerRepository;
import com.example.warehouse_management.service.dto.CustomerDto;
import com.example.warehouse_management.service.mapper.CustomerMapper;
import com.example.warehouse_management.service.request.CustomerFilterRequest;
import com.example.warehouse_management.service.specification.CustomerSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public CustomerDto create(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        customer.setCustomerStatus(CustomerStatus.ACTIVE);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    public CustomerDto update(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    public List<CustomerDto> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    public CustomerDto get(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        return customerMapper.toDto(customer);
    }

    public CustomerDto delete(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setCustomerStatus(CustomerStatus.DELETED);
        Customer updatedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(updatedCustomer);
    }

    public CustomerDto deleted(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        customerRepository.delete(customer);
        return customerMapper.toDto(customer);
    }

    public Page<Customer> findAllWithFilter(CustomerFilterRequest request, Pageable pageable) {
        Specification<Customer> spec = CustomerSpecification.filter(request);
        return customerRepository.findAll(
                spec,
                pageable
        );
    }

    public Page<Customer> getAll(Pageable pageable) {
        return customerRepository.findAllCustomers(pageable);
    }
}
