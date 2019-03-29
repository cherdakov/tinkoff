package repository.customer;

import entity.Customer;
import entity.ResponseData;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.UUID;

public interface CustomerHttpRepository {

    @GET("/customers")
    Call<ResponseData<List<Customer>>> getCustomers();

    @GET("/customer/{id}")
    Call<ResponseData<Customer>> getCustomer(@Path("id") UUID id);

    @POST("/customer/{id}")
    Call<ResponseData<String>> updateCustomer(@Path("id)") UUID id, @Body Customer customer);

    @POST("/customers")
    Call<ResponseData<UUID>> addCustomer(@Body Customer customer);

    @DELETE("/customer/{id}")
    Call<ResponseData<String>> deleteCustomer(@Path("id") UUID id);
}