package com.g_draflab.orderit.Fragments;


import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.g_draflab.orderit.Adapter.ProductsFragmentAdapter;
import com.g_draflab.orderit.Models.Category;
import com.g_draflab.orderit.R;
import com.g_draflab.orderit.Retrofit.ApiUtils;
import com.g_draflab.orderit.Retrofit.CategoryService;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductContainer extends Fragment {

    public ProductContainer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_container, container, false);
        int departmentId = getArguments().getInt("departmentId");

        // Inflate the layout for this fragment
        return view;
    }
}
