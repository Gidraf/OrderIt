package com.g_draflab.orderit.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.g_draflab.orderit.Adapter.ColorAttributeAdapter;
import com.g_draflab.orderit.Adapter.LauncherNavigationAdapter;
import com.g_draflab.orderit.Adapter.SizeAttributeAdapter;
import com.g_draflab.orderit.Interfaces.AttributeListener;
import com.g_draflab.orderit.Models.Attribute;
import com.g_draflab.orderit.Models.AttributesServices;
import com.g_draflab.orderit.Models.CartId;
import com.g_draflab.orderit.Models.CartResponse;
import com.g_draflab.orderit.Models.Product;
import com.g_draflab.orderit.R;
import com.g_draflab.orderit.Retrofit.ApiUtils;
import com.g_draflab.orderit.Retrofit.CartServices;
import com.g_draflab.orderit.Retrofit.ProductsServices;
import com.g_draflab.orderit.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {
    ProductsServices productsServices;
    TextView productDescription, discountPrice, realPrice, titleBar;
    ViewPager productDescriptionViewPager;
    TabLayout productTabLayout;
    Button addToCartButton;
    ImageView closseImage;
    LinearLayout customButton;
    LauncherNavigationAdapter adapter;
    SpotsDialog.Builder dialog;
    AlertDialog builder;
    AttributesServices attributesServices;
    RecyclerView colorRecyclerView, sizeRecyclerView;
    ColorAttributeAdapter colorAttributeAdapter;
    SizeAttributeAdapter sizeAttributeAdapter;
    RecyclerView.LayoutManager colorManger, sizeManger;
    LinearLayout attribute_holder;
    CartServices cartServices;
    String cartId;
    int shortAnimationDuration;
    StringBuilder attributesString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        dialog = new SpotsDialog.Builder().setContext(this);
        dialog.setTheme(R.style.sports_alert_dialog);
        closseImage = findViewById(R.id.product_close);
        Paper.init(this);
        cartId = Paper.book().read("cartId");
        productDescription = findViewById(R.id.product_description_text_view);
        discountPrice = findViewById(R.id.product_discount_price_text_view);
        realPrice =  findViewById(R.id.product_price_text_view);
        realPrice.setPaintFlags(realPrice.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        adapter = new LauncherNavigationAdapter(getSupportFragmentManager());
        productDescriptionViewPager = findViewById(R.id.product_viewPager);
        productTabLayout = findViewById(R.id.product_indicator);
        productDescriptionViewPager.setAdapter(adapter);
        productTabLayout.setupWithViewPager(productDescriptionViewPager);
        addToCartButton = findViewById(R.id.add_to_cart);
        attributesString = new StringBuilder();
        customButton =  findViewById(R.id.customize_holder);
        titleBar = findViewById(R.id.title_text_view);
        attribute_holder = findViewById(R.id.attribute_holder);
        shortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);
        int productId = -1;
        cartServices = ApiUtils.cartServices();
        attributesServices = ApiUtils.attributesServices();
        colorRecyclerView = findViewById(R.id.product_color_recycler_view);
        sizeRecyclerView =  findViewById(R.id.product_size_recycler_view);
        colorManger = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        sizeManger = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        colorRecyclerView.setHasFixedSize(true);
        sizeRecyclerView.setHasFixedSize(true);
        colorAttributeAdapter = new ColorAttributeAdapter(this, this);
        sizeAttributeAdapter = new SizeAttributeAdapter(this, this);
        closseImage.setOnClickListener(this);
        sizeAttributeAdapter.setAttributeListener(attribute -> {
            Toast.makeText(ProductActivity.this, attribute, Toast.LENGTH_SHORT).show();
            attributesString.append(attribute);
        });
        colorAttributeAdapter.setAttributeListener(attribute -> {
            Toast.makeText(ProductActivity.this, attribute, Toast.LENGTH_SHORT).show();
            attributesString.append(attribute);
        });
        customButton.setOnClickListener(this);
        colorRecyclerView.setLayoutManager(colorManger);
        sizeRecyclerView.setLayoutManager(sizeManger);
        sizeRecyclerView.setAdapter(sizeAttributeAdapter);
        colorRecyclerView.setAdapter(colorAttributeAdapter);
        productsServices = ApiUtils.productsServices();
        if(getIntent()!=null){
            productId = getIntent().getIntExtra("productId", -1);
        }
        if(productId != -1 ){
            int finalProductId = productId;
            addToCartButton.setOnClickListener(v -> addProductToCart(finalProductId));
            fetchProductInformation(productId);
        }
    }

    private void addProductToCart(int finalProductId) {
        if(cartId != null && finalProductId!=-1) {
            if(attributesString.length()==0){
                Toast.makeText(this,"Please choose on attribute by tapping customize", Toast.LENGTH_SHORT).show();
                return;
            }
            showProgressBar();
            cartServices.addProductToCart(cartId, finalProductId, attributesString.toString()).enqueue(new Callback<List<CartResponse>>() {
                @Override
                public void onResponse(Call<List<CartResponse>> call, Response<List<CartResponse>> response) {
                    if(response.isSuccessful()) {
                        dismissProgrssBar();
                        dismissProgrssBar();
                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ProductActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setCancelable(false);
                        sweetAlertDialog.setContentText("Successfullt Added");
                        sweetAlertDialog.setConfirmText("Check it Out").setConfirmClickListener(
                                sweetAlertDialog12 -> {
//                                                            startActivity(new Intent(ProductActivity.this, SignInActivity.class));
//                                                            finish();
                                }
                        ).setNeutralButton("Continue Shopping", Dialog::dismiss).show();
                        return;
                    }
                    dismissProgrssBar();
                    Toast.makeText(ProductActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<List<CartResponse>> call, Throwable t) {
                    dismissProgrssBar();

                }
            });


        }
    }

    private void fetchProductInformation(final int productId) {
        showProgressBar();
        attributesServices.getProductAttribute(productId).enqueue(new Callback<List<Attribute>>() {
            @Override
            public void onResponse(Call<List<Attribute>> call, Response<List<Attribute>> response) {
                if(response.isSuccessful()){
                    List<Attribute> colorAttributes = new ArrayList<>();
                    List<Attribute> sizeAttrubutes = new ArrayList<>();
                    for(int i=0; i<10;i++){
                        colorAttributes.add(response.body().get(i));
                    }
                    for(int i=10; i<response.body().size();i++){
                        sizeAttrubutes.add(response.body().get(i));
                    }
                    colorAttributeAdapter.setAttributeList(colorAttributes);
                    sizeAttributeAdapter.setAttributeList(sizeAttrubutes);
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<Attribute>> call, Throwable t) {

            }
        });
        productsServices.getProductInfo(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if(response.isSuccessful()){
                    dismissProgrssBar();
                    productDescription.setText(response.body().getDescription());
                    realPrice.setText("$" +response.body().getPrice());
                    discountPrice.setText("$"+response.body().getDiscountedPrice());
                    titleBar.setText(response.body().getName());
                    return;
                }
                dismissProgrssBar();
                Toast.makeText(ProductActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                dismissProgrssBar();
                Toast.makeText(ProductActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View v) {
        if (v==customButton){
            if(attribute_holder.getVisibility()==View.VISIBLE){
                attribute_holder.animate().alpha(0f)
                        .setDuration(shortAnimationDuration)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                attribute_holder.setVisibility(View.GONE);
                            }
                        });
                return;
            }
            attribute_holder.animate().alpha(1f)
                    .setDuration(shortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            attribute_holder.setVisibility(View.VISIBLE);
                        }
                    });
        }
        else if(v==closseImage){
            finish();
        }
    }

}
