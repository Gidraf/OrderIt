package com.g_draflab.orderit.Retrofit;

import com.g_draflab.orderit.Models.Product;
import com.g_draflab.orderit.Models.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductsServices {
    @GET("/products/inCategory/{category_id}")
    Call<ProductResponse> getproductsCategories(@Path("category_id") int categoryId, @Query("page") int page,
                                                @Query("limit") int limit,
                                                @Query("description_length") int descriptionLength);

    @GET("/products/inDepartment/{category_id}")
    Call<ProductResponse> getAllProducts(@Path("category_id") int categoryId, @Query("page") int page,
                                         @Query("limit") int limit,
                                         @Query("description_length") int descriptionLength);

    @GET("/products/{product_id}")
    Call<Product> getProductInfo(@Path("product_id") int product_id);
}
