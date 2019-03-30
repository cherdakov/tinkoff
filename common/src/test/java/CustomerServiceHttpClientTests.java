import client.CustomerServiceHttpClient;
import entity.Customer;
import entity.ResponseData;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

public class CustomerServiceHttpClientTests {

    CustomerServiceHttpClient client = new CustomerServiceHttpClient();

    Customer createCustomer(){
        Customer customer = new Customer();
        //customer.setId(UUID.randomUUID());
        customer.setName("Kirill");
        return customer;
    }

    @Test
    public void addCustomerTest() throws IOException {
        Customer customer = createCustomer();
        ResponseData<UUID> addResponse = client.addCustomer(customer);
        UUID id = addResponse.getData();
        customer.setId(id);
        ResponseData<Customer> getResponse = client.getCustomer(id);
        Customer gotten = getResponse.getData();
        Assert.assertEquals(customer, gotten);
    }
}
