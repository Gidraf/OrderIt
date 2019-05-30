package com.g_draflab.orderit.Retrofit;

import com.g_draflab.orderit.Models.OrderId;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OrderServices {
    @POST("/orders")
    @FormUrlEncoded
    Call<OrderId> getOrderId(@Field("cart_id") String cartId,
                             @Field("shipping_id") int shippingId,
                             @Field("tax_id") int taxId, @Header("USER-KEY") String token);
}
