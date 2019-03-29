package com.tinkoff.customerservice;

import entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@EntityScan(basePackages = "entity")
public class CustomerServiceApplicationTests {

    @Autowired
    CustomerService customerService;

    public final String TEST_CUSTOMER_NAME = "TEST_NAME";

    Customer getTestCustomer(){

        Customer customer = new Customer();
        customer.setName(TEST_CUSTOMER_NAME);

        return customer;
    }

    @Test
    public void crudTests() throws CustomerServiceException {

    }

}
