package com.g_draflab.orderit.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.g_draflab.orderit.Activities.CheckoutActivity;
import com.g_draflab.orderit.Adapter.CartItemAdapter;
import com.g_draflab.orderit.Models.CartResponse;
import com.g_draflab.orderit.Models.CartTotal;
import com.g_draflab.orderit.R;
import com.g_draflab.orderit.Retrofit.ApiUtils;
import com.g_draflab.orderit.Retrofit.CartServices;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Objects;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BagFragment extends Fragment {

    RecyclerView itemsrecyclerView;
    CartItemAdapter adapter;
    RecyclerView.LayoutManager manager;
    RelativeLayout noItemView, contentView;
    TextView subtotal, totalAmount;
    TextView itemCount;
    CartServices cartServices;
    SpotsDialog.Builder dialog;
    Button checkoutBtn;
    AlertDialog builder;

    public BagFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Paper.init(Objects.requireNonNull(getContext()));
        String cartId = Paper.book().read("cartId");
        dialog = new SpotsDialog.Builder().setContext(getContext());
        dialog.setTheme(R.style.sports_alert_dialog);
        View view = inflater.inflate(R.layout.fragment_bag, container, false);
        itemsrecyclerView = view.findViewById(R.id.cart_recycler);
        manager = new LinearLayoutManager(getContext());
        adapter = new CartItemAdapter(getContext());
        cartServices = ApiUtils.cartServices();
        subtotal = view.findViewById(R.id.subtotal_text_view);
        totalAmount = view.findViewById(R.id.total_text_view);
        noItemView = view.findViewById(R.id.no_item_view);
        contentView = view.findViewById(R.id.cart_content_view);
        itemCount = view.findViewById(R.id.total_cart_items_text_view);
        itemsrecyclerView.setLayoutManager(manager);
        itemsrecyclerView.setHasFixedSize(true);
        checkoutBtn = view.findViewById(R.id.checkout_btn);
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CheckoutActivity.class));
            }
        });
        itemsrecyclerView.setAdapter(adapter);
        if(cartId==null){
            noItemView.setVisibility(View.VISIBLE);
            contentView.setVisibility(View.GONE);
        }
        else {
            loadData(cartId);
        }
        return view;
    }

    private void loadData(String cartId) {
        showProgressBar();
        cartServices.getCartTotalAmount(cartId).enqueue(new Callback<CartTotal>() {
            @Override
            public void onResponse(Call<CartTotal> call, Response<CartTotal> response) {
                if(response.isSuccessful()){
                    subtotal.setText("$"+response.body().getTotalAmount());
                    totalAmount.setText("$"+response.body().getTotalAmount());
                }
            }

            @Override
            public void onFailure(Call<CartTotal> call, Throwable t) {

            }
        });
        cartServices.getCartItems(cartId).enqueue(new Callback<List<CartResponse>>() {
            @Override
            public void onResponse(Call<List<CartResponse>> call, Response<List<CartResponse>> response) {
                if(response.isSuccessful()){
                    if(!response.body().isEmpty()) {
                        dismissProgrssBar();
                        noItemView.setVisibility(View.GONE);
                        contentView.setVisibility(View.VISIBLE);
                        adapter.setCartResponses(response.body());
                        itemCount.setText(String.valueOf(response.body().size())+" items");
                        return;
                    }
                    dismissProgrssBar();
                    noItemView.setVisibility(View.VISIBLE);
                    contentView.setVisibility(View.GONE);
                    return;
                }
                dismissProgrssBar();
                noItemView.setVisibility(View.VISIBLE);
                contentView.setVisibility(View.GONE);
                Toast.makeText(getContext(), "An error occured", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {
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
