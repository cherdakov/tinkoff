package com.tinkoff.customerservice.repository;

import com.tinkoff.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
