package com.tinkoff.customerservice.controller;

import com.tinkoff.customerservice.CustomerService;
import com.tinkoff.customerservice.CustomerServiceException;
import entity.Customer;
import entity.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(
            value = {"/customers"},
            method = {RequestMethod.GET}
    )
    public ResponseData<List<Customer>> getCustomers(){
        return customerService.getCustomers();
    }

    @RequestMapping(
            value = {"/customer/{id}"},
            method = {RequestMethod.GET}
    )
    public ResponseData<Customer> getCustomer(@PathVariable("id") UUID id) throws CustomerServiceException {
        return customerService.getCustomer(id);
    }

    @RequestMapping(
            value = {"/customer/{id}"},
            method = {RequestMethod.POST}
    )
    public ResponseData<UUID> updateCustomer(@PathVariable("id") UUID id, @RequestBody Customer customer) throws CustomerServiceException {
        return customerService.updateCustomer(customer);
    }

    @RequestMapping(
            value = {"/customers"},
            method = {RequestMethod.POST}
    )
    public ResponseData<UUID> addCustomer(@RequestBody Customer customer) throws CustomerServiceException {
        return customerService.addCustomer(customer);
    }

    @RequestMapping(
            value = {"/customer/{id}"},
            method = {RequestMethod.DELETE}
    )
    public ResponseData<UUID> deleteCustomer(@PathVariable("id") UUID id) throws CustomerServiceException {
        return customerService.deleteCustomer(id);
    }
}
