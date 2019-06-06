package com.g_draflab.orderit.Fragments;


import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.g_draflab.orderit.Adapter.EndlessScrollListener;
import com.g_draflab.orderit.Adapter.ProductRecyclerViewsAdapter;
import com.g_draflab.orderit.Interfaces.GetCurrentPagePositionListener;
import com.g_draflab.orderit.Interfaces.OnLoadMore;
import com.g_draflab.orderit.Models.Product;
import com.g_draflab.orderit.Models.ProductResponse;
import com.g_draflab.orderit.R;
import com.g_draflab.orderit.Retrofit.ApiUtils;
import com.g_draflab.orderit.Retrofit.ProductsServices;
import com.g_draflab.orderit.Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment implements View.OnClickListener {
    private static final String BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout";
    RecyclerView productsRecyclerView;
    ProductRecyclerViewsAdapter adapter;
    SpotsDialog.Builder dialog;
    RecyclerView.LayoutManager manager;
    AlertDialog builder;
    ProductsServices productsServices;
    List<Product> products= new ArrayList<>();
    int page = 1;
    int categoryId;
    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState != null)
        {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            productsRecyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dialog = new SpotsDialog.Builder().setContext(getContext());
        dialog.setTheme(R.style.sports_alert_dialog);
        productsServices = ApiUtils.productsServices();
        categoryId = getArguments().getInt("categoryId");
        if(categoryId == 0){
            loadAllProducts(Constants.currentDepartment);
        }
        else {
            loadAllProductsInCategory(categoryId);
        }
        View view =  inflater.inflate(R.layout.fragment_products, container, false);
        manager = new LinearLayoutManager(getContext());
        manager.onSaveInstanceState();
        productsRecyclerView = view.findViewById(R.id.products_recycler_view);
        productsRecyclerView.setLayoutManager(manager);
        productsRecyclerView.setHasFixedSize(true);
        adapter = new ProductRecyclerViewsAdapter(getContext(), this, productsRecyclerView);
        productsRecyclerView.setAdapter(adapter);

        productsRecyclerView.addOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore() {
                if(categoryId == 0){
                    loadAllProducts(Constants.currentDepartment);
                }
                else {
                    loadAllProductsInCategory(categoryId);
                }
            }
        });
        return  view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, productsRecyclerView.getLayoutManager().onSaveInstanceState());
    }

    private void loadAllProducts(int departmentId) {
        showProgressBar();
        int currentPage = page++;
        productsServices.getAllProducts(departmentId, currentPage, 10,100).enqueue(new Callback<ProductResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getCount()!=0) {
                        products = Stream.concat(products.stream(), response.body().getProductList().stream())
                                .collect(Collectors.toList());
                        adapter.setProducts(products);
                        dismissProgrssBar();
                        return;
                    }
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
        int currentPage = page++;
        productsServices.getproductsCategories(categoryId, currentPage, 2,50 ).enqueue(new Callback<ProductResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getCount()!=0) {
                        List<Product> productList = Stream.concat(products.stream(), response.body().getProductList().stream())
                                .collect(Collectors.toList());
                        adapter.setProducts(productList);
                        dismissProgrssBar();
                        return;
                    }
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

    @Override
    public void onPause() {
        super.onPause();
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


    @Override
    public void onClick(View v) {

    }
}
