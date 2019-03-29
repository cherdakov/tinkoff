package com.tinkoff.customerservice;

import entity.Customer;
import org.junit.Assert;
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

        int count = customerService.getCustomers().size();
        Customer customer = getTestCustomer();
        customer = customerService.addCustomer(customer);
        Assert.assertEquals(count + 1, customerService.getCustomers().size());
        Assert.assertNotNull(customerService.getCustomer(customer.getId()));
        final String NEW_NAME = "NEW_NAME";
        customer.setName(NEW_NAME);
        customerService.updateCustomer(customer);
        customer = customerService.getCustomer(customer.getId());
        Assert.assertEquals(NEW_NAME, customer.getName());
        customerService.deleteCustomer(customer.getId());
        Assert.assertEquals(count, customerService.getCustomers().size());
    }

}
