package com.tinkoff.customerservice;


import entity.Customer;
import entity.ResponseData;
import entity.ResultCode;
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
        return new ResponseData<>(customers, ResultCode.OK);
    }

    @Override
    public ResponseData<UUID> deleteCustomer(UUID id) throws CustomerServiceException {

        validateExists(id);
        customerRepository.deleteById(id);
        return new ResponseData<>(id, ResultCode.OK);
    }

    @Override
    public ResponseData<UUID> addCustomer(Customer customer) throws CustomerServiceException {
        if(customer.getId()!=null){
            throw new CustomerServiceException("Customer must haven't id for creating", ResultCode.ERROR);
        }
        Customer result = customerRepository.save(customer);
        return new ResponseData<>(result.getId(), ResultCode.OK);
    }

    @Override
    public ResponseData<Customer> getCustomer(UUID id) throws CustomerServiceException {
        validateExists(id);
        Customer customer = customerRepository.getOne(id);
        return new ResponseData<>(customer, ResultCode.OK);
    }

    @Override
    public ResponseData<UUID> updateCustomer(Customer customer) throws CustomerServiceException {
        if(customer.getId() == null){
            throw new CustomerServiceException("Customer must have id for updating", ResultCode.ERROR);
        }
        customerRepository.save(customer);
        return new ResponseData<>(customer.getId(), ResultCode.OK);
    }

    private void validateExists(UUID id) throws CustomerServiceException {
        if (!customerRepository.existsById(id)) {
            throw new CustomerServiceException("Customer with id " + id + " doesn't exist", ResultCode.NOT_EXISTS);
        }
    }
}
