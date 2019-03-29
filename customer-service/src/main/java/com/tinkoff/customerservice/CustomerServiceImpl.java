package com.tinkoff.customerservice;


import entity.Customer;
import entity.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.customer.CustomerRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ResponseData<List<Customer>> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return new ResponseData<>(customers, ResponseData.ResultCode.OK);
    }

    @Override
    public ResponseData<UUID> deleteCustomer(UUID id) throws CustomerServiceException {

        validateExists(id);
        customerRepository.deleteById(id);
        return new ResponseData<>(id, ResponseData.ResultCode.OK);
    }

    @Override
    public ResponseData<UUID> addCustomer(Customer customer) {
        Customer result = customerRepository.save(customer);
        return new ResponseData<UUID>(result.getId(), ResponseData.ResultCode.OK);
    }

    @Override
    public ResponseData<Customer> getCustomer(UUID id) throws CustomerServiceException {
        validateExists(id);
        Customer customer = customerRepository.getOne(id);
        return new ResponseData<>(customer, ResponseData.ResultCode.OK);
    }

    @Override
    public ResponseData<UUID> updateCustomer(Customer customer) throws CustomerServiceException {
        if(customer.getId() == null){
            throw new CustomerServiceException("Customer must have id for updating");
        }
        customerRepository.save(customer);
        return new ResponseData<>(customer.getId(), ResponseData.ResultCode.OK);
    }

    private void validateExists(UUID id) throws CustomerServiceException {
        if (!customerRepository.existsById(id)) {
            throw new CustomerServiceException("Customer with id " + id + " doesn't exist");
        }
    }
}
