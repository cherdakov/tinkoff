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
            .baseUrl("https://localhost:1234/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build();

    CustomerServiceHttpClient(){

    }

    ResponseData<List<Customer>> getCustomers() throws IOException {
        CustomerHttpRepository repository = retrofit.create(CustomerHttpRepository.class);
        Call<ResponseData<List<Customer>>> call = repository.getCustomers();
        Response<ResponseData<List<Customer>>> response = call.execute();
        ResponseData<List<Customer>> responseData = response.body();
        return responseData;
    }

    ResponseData<Customer> getCustomer(UUID id) throws IOException {
        CustomerHttpRepository repository = retrofit.create(CustomerHttpRepository.class);
        Call<ResponseData<Customer>> call = repository.getCustomer(id);
        Response<ResponseData<Customer>> response = call.execute();
        ResponseData<Customer> responseData = response.body();
        return responseData;
    }

    /*

    @POST("/customer/{id}")
    Call<ResponseData<String>> updateCustomer(@Path("id") UUID id);

    @POST("/customers")
    Call<ResponseData<UUID>> addCustomer(@Body Customer customer);

    @DELETE("/customer/{id}")
    Call<ResponseData<String>> deleteCustomer(@Path("id") UUID id);

    */

    ResponseData<UUID> addCustomer(Customer customer) throws IOException {
        CustomerHttpRepository repository = retrofit.create(CustomerHttpRepository.class);
        Call<ResponseData<UUID>> call = repository.addCustomer(customer);
        Response<ResponseData<UUID>> response = call.execute();
        ResponseData<UUID> responseData = response.body();
        return responseData;
    }

}
