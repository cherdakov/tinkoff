package com.tinkoff.customerservice.controller;

import com.tinkoff.customerservice.CustomerService;
import com.tinkoff.customerservice.entity.Customer;
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
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @RequestMapping(
            value = {"/customer/{id}"},
            method = {RequestMethod.GET}
    )
    public Customer getCustomer(@PathVariable("id") UUID id) throws CustomerServiceException {
        return customerService.getCustomer(id);
    }

    @RequestMapping(
            value = {"/customer/{id}"},
            method = {RequestMethod.POST}
    )
    public void updateCustomer(@PathVariable("id") UUID id, @RequestBody Customer customer) throws CustomerServiceException {
        customerService.updateCustomer(customer);
    }

    @RequestMapping(
            value = {"/customers"},
            method = {RequestMethod.POST}
    )
    public void addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
    }

    @RequestMapping(
            value = {"/customer/{id}"},
            method = {RequestMethod.DELETE}
    )
    public void deleteCustomer(@PathParam("id") UUID id) throws CustomerServiceException {
        customerService.deleteCustomer(id);
    }
}
