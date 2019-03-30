package client;

import entity.Customer;
import entity.ResponseData;
import okhttp3.OkHttpClient;
import repository.customer.CustomerHttpRepository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class CustomerServiceHttpClient {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8765/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();
    private CustomerHttpRepository repository = retrofit.create(CustomerHttpRepository.class);

    public CustomerServiceHttpClient(){

    }

    public ResponseData<List<Customer>> getCustomers() throws IOException {
        Call<ResponseData<List<Customer>>> call = repository.getCustomers();
        Response<ResponseData<List<Customer>>> response = call.execute();
        ResponseData<List<Customer>> responseData = response.body();
        return responseData;
    }

    public ResponseData<Customer> getCustomer(UUID id) throws IOException {
        Call<ResponseData<Customer>> call = repository.getCustomer(id);
        Response<ResponseData<Customer>> response = call.execute();
        ResponseData<Customer> responseData = response.body();
        return responseData;
    }

    public ResponseData<UUID> updateCustomer(Customer customer) throws IOException {
        Call<ResponseData<UUID>> call = repository.addCustomer(customer);
        Response<ResponseData<UUID>> response = call.execute();
        ResponseData<UUID> responseData = response.body();
        return responseData;
    }

    public ResponseData<UUID> deleteCustomer(UUID id) throws IOException {
        Call<ResponseData<UUID>> call = repository.deleteCustomer(id);
        Response<ResponseData<UUID>> response = call.execute();
        ResponseData<UUID> responseData = response.body();
        return responseData;
    }

    public ResponseData<UUID> addCustomer(Customer customer) throws IOException {
        Call<ResponseData<UUID>> call = repository.updateCustomer(customer.getId(), customer);
        Response<ResponseData<UUID>> response = call.execute();
        ResponseData<UUID> responseData = response.body();
        return responseData;
    }

}
