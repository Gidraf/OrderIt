package com.g_draflab.orderit.Retrofit;

import com.g_draflab.orderit.Models.Customer;
import com.g_draflab.orderit.Models.CustomerRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface AuthServices {

    @POST("/customers")
    @FormUrlEncoded
    Call<CustomerRegisterResponse> registerCustomer(@Field("name") String name,
                                                    @Field("email") String email,
                                                    @Field("password") String password);

    @POST("/customers/login")
    @FormUrlEncoded
    Call<CustomerRegisterResponse> loginCustomer(@Field("email") String email,
                                                 @Field("password") String password);

    @POST("/customers/facebook")
    @FormUrlEncoded
    Call<CustomerRegisterResponse> facebookloginCustomer(@Field("accesss_token") String token);

    @POST("/customers/forget")
    @FormUrlEncoded
    Call<CustomerRegisterResponse> forgetpassword(@Field("email") String email);

    @GET("/customer")
    Call<Customer> getCustomer(@Header("USER-KEY") String token);

    @PUT("/customers/address")
    @FormUrlEncoded
    Call<Customer> editCustomerAddress(@Field("address_1") String address1,
                                       @Field("address_2") String address2,
                                       @Field("city") String city,
                                       @Field("region") String region,
                                       @Field("postal_code") String postalCode,
                                       @Field("country") String country,
                                       @Field("shipping_region_id") int shippingRegionId,
                                       @Header("USER-KEY") String token);
}
