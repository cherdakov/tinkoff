package com.tinkoff.customerservice;

import com.tinkoff.customerservice.entity.Customer;
import com.tinkoff.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    @Override
    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        Customer result = customerRepository.save(customer);
        return result;
    }

    @Override
    public Customer getCustomer(UUID id) {
        Customer customer = customerRepository.getOne(id);
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
