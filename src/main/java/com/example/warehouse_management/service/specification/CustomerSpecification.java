package com.example.warehouse_management.service.specification;

import com.example.warehouse_management.entity.Customer;
import com.example.warehouse_management.service.request.CustomerFilterRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class CustomerSpecification {

    public static Specification<Customer> filter(CustomerFilterRequest filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getCustomerName() != null && !filter.getCustomerName().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("customerName")), "%" + filter.getCustomerName().toLowerCase() + "%"));
            }

            if (filter.getCompanyName() != null && !filter.getCompanyName().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("companyName")), "%" + filter.getCompanyName().toLowerCase() + "%"));
            }

            if (filter.getPhoneNumber() != null && !filter.getPhoneNumber().isEmpty()) {
                predicates.add(cb.like(root.get("phoneNumber"), "%" + filter.getPhoneNumber() + "%"));
            }

            if (filter.getAddress() != null && !filter.getAddress().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("address")), "%" + filter.getAddress().toLowerCase() + "%"));
            }

            if (filter.getCustomerStatus() != null) {
                predicates.add(cb.equal(root.get("customerStatus"), filter.getCustomerStatus()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

