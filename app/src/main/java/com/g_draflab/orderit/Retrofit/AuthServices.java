package com.g_draflab.orderit.Retrofit;

import com.g_draflab.orderit.Models.CustomerRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}
