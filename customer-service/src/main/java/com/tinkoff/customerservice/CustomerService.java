package com.tinkoff.customerservice;

import com.tinkoff.customerservice.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<Customer> getCustomers();

    void deleteCustomer(UUID id);

    Customer addCustomer(Customer customer);

    Customer getCustomer(UUID id);

    void updateCustomer(Customer customer);
}
