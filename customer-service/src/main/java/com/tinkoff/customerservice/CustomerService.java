package com.tinkoff.customerservice;


import entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<Customer> getCustomers();

    void deleteCustomer(UUID id) throws CustomerServiceException;

    Customer addCustomer(Customer customer);

    Customer getCustomer(UUID id) throws CustomerServiceException;

    void updateCustomer(Customer customer) throws CustomerServiceException;
}
