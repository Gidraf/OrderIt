package com.g_draflab.orderit.Retrofit;

import com.g_draflab.orderit.Models.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductsServices {
    @GET("/products/inCategory/{category_id}?page=1&limit=10&description_length=150")
    Call<ProductResponse> getproductsCategories(@Path("category_id") int categoryId);

    @GET("https://backendapi.turing.com/products/inDepartment/{category_id}?page=1&limit=20&description_length=50")
    Call<ProductResponse> getAllProducts(@Path("category_id") int categoryId);

}
