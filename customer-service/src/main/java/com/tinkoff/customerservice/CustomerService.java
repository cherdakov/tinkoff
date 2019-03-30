package com.tinkoff.customerservice;


import entity.Customer;
import entity.ResponseData;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    ResponseData<List<Customer>> getCustomers();

    ResponseData<UUID> deleteCustomer(UUID id) throws CustomerServiceException;

    ResponseData<UUID> addCustomer(Customer customer) throws CustomerServiceException;

    ResponseData<Customer> getCustomer(UUID id) throws CustomerServiceException;

    ResponseData<UUID> updateCustomer(Customer customer) throws CustomerServiceException;
}
