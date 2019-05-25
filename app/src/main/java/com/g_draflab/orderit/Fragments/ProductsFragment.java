package com.g_draflab.orderit.Fragments;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.g_draflab.orderit.Adapter.ProductRecyclerViewsAdapter;
import com.g_draflab.orderit.Interfaces.GetCurrentPagePositionListener;
import com.g_draflab.orderit.Models.Product;
import com.g_draflab.orderit.Models.ProductResponse;
import com.g_draflab.orderit.R;
import com.g_draflab.orderit.Retrofit.ApiUtils;
import com.g_draflab.orderit.Retrofit.ProductsServices;
import com.g_draflab.orderit.Utils.Constants;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {

    GetCurrentPagePositionListener listener;
    RecyclerView productsRecyclerView;
    ProductRecyclerViewsAdapter adapter;
    SpotsDialog.Builder dialog;
    RecyclerView.LayoutManager manager;
    AlertDialog builder;
    ProductsServices productsServices;
    List<Product> products;

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dialog = new SpotsDialog.Builder().setContext(getContext());
        dialog.setTheme(R.style.sports_alert_dialog);
        productsServices = ApiUtils.productsServices();
        int categoryId = getArguments().getInt("categoryId");
        if(categoryId == 0){
            loadAllProducts(Constants.currentDepartment);
        }
        else {
            loadAllProductsInCategory(categoryId);
        }
        View view =  inflater.inflate(R.layout.fragment_products, container, false);
        manager = new LinearLayoutManager(getContext());
        productsRecyclerView = view.findViewById(R.id.products_recycler_view);
        productsRecyclerView.setLayoutManager(manager);
        productsRecyclerView.setHasFixedSize(true);
        adapter = new ProductRecyclerViewsAdapter(getContext());
        productsRecyclerView.setAdapter(adapter);
        return  view;
    }

    private void loadAllProducts(int departmentId) {
        showProgressBar();
        productsServices.getAllProducts(departmentId).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    if(products!= null){
                        products.clear();
                    }
                    products =  response.body().getProductList();
                    adapter.setProducts(products);
                    dismissProgrssBar();
                    return;
                }
                dismissProgrssBar();
                Toast.makeText(getContext(), "an error Occurred", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                dismissProgrssBar();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadAllProductsInCategory(int categoryId) {
        showProgressBar();
        productsServices.getproductsCategories(categoryId).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    if(products!= null){
                        products.clear();
                    }
                    products =  response.body().getProductList();
                    adapter.setProducts( products);
                    dismissProgrssBar();
                    return;
                }
                dismissProgrssBar();
                Toast.makeText(getContext(), "an error Occurred", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                dismissProgrssBar();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showProgressBar(){
        dialog.setMessage(getResources().getString(R.string.loading));
        dialog.setCancelable(false);
        builder = dialog.build();
        builder.show();
    }

    public void dismissProgrssBar(){
        if(builder.isShowing()){
            builder.dismiss();
        }
    }

}
