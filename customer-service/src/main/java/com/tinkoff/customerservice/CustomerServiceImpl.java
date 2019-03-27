package com.tinkoff.customerservice;


import com.tinkoff.customerservice.repository.CustomerRepository;
import entity.Customer;
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
    public void deleteCustomer(UUID id) throws CustomerServiceException {
        validateExists(id);
        customerRepository.deleteById(id);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        Customer result = customerRepository.save(customer);
        return result;
    }

    @Override
    public Customer getCustomer(UUID id) throws CustomerServiceException {
        validateExists(id);
        Customer customer = customerRepository.getOne(id);
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) throws CustomerServiceException {
        if(customer.getId() == null){
            throw new CustomerServiceException("Customer must have id for updating");
        }
        customerRepository.save(customer);
    }

    private void validateExists(UUID id) throws CustomerServiceException {
        if (!customerRepository.existsById(id)) {
            throw new CustomerServiceException("Customer with id " + id + " doesn't exist");
        }
    }
}
