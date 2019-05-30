package com.g_draflab.orderit.Models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AttributesServices {

    @GET("attributes/inProduct/{product_id}")
    Call<List<Attribute>> getProductAttribute(@Path("product_id") int productId);
}
