package com.g_draflab.orderit.Retrofit;

import com.g_draflab.orderit.Models.StripeResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface StripeServices {
    @POST("/stripe/charge")
    @FormUrlEncoded
    Call<StripeResponse> postCharges(@Field("stripeToken") String stripeToken,
                                     @Field("order_id") int orderId,
                                     @Field("description") String description,
                                     @Field("amount") int amount);
}
