package com.g_draflab.orderit.Retrofit;

import com.g_draflab.orderit.Models.Department;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DepartmentsServices {
    @GET("/departments")
    Call<List<Department>> getAllDepartments();
}
