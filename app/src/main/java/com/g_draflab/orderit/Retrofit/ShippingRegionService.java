package com.g_draflab.orderit.Retrofit;

import com.g_draflab.orderit.Models.Shipping;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShippingRegionService {
    @GET("/shipping/regions")
    Call<List<Shipping>> getShippingRegions();
}
