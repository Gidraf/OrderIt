package com.g_draflab.orderit.Retrofit;

import com.g_draflab.orderit.Models.CartId;
import com.g_draflab.orderit.Models.CartResponse;
import com.g_draflab.orderit.Models.CartTotal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CartServices {
    @GET("/shoppingcart/generateUniqueId")
    Call<CartId> getCartId();

    @POST("/shoppingcart/add")
    @FormUrlEncoded
    Call<List<CartResponse>> addProductToCart(@Field("cart_id") String cartIdd,
                                             @Field("product_id") int productId,
                                             @Field("attributes") String attributes);

    @GET("/shoppingcart/{cart_id}")
    Call<List<CartResponse>> getCartItems(@Path("cart_id") String cartId);

    @GET("/shoppingcart/totalAmount/{cart_id}")
    Call<CartTotal> getCartTotalAmount(@Path("cart_id") String cartId);
}
