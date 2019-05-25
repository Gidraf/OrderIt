package com.g_draflab.orderit.Retrofit;

import com.g_draflab.orderit.Models.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryService {
    @GET("/categories/inDepartment/{category_id}")
    Call<List<Category>> getallCategries(@Path("category_id") int category_id);
}
