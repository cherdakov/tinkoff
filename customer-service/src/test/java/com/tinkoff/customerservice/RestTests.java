package com.tinkoff.customerservice;

import entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestTests {

    @Autowired
    CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void test() throws Exception {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setName("Kirill");
        mockMvc.perform(get("/customer/{id}", customer.getId()))
                .andExpect(status().isOk())
                .andReturn();
    }
}