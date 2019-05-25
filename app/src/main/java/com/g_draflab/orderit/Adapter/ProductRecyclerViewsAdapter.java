package com.g_draflab.orderit.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.g_draflab.orderit.Interfaces.GetAllProductsListener;
import com.g_draflab.orderit.Interfaces.GetCurrentPagePositionListener;
import com.g_draflab.orderit.Models.Product;
import com.g_draflab.orderit.Models.ProductResponse;
import com.g_draflab.orderit.R;
import com.g_draflab.orderit.Retrofit.ApiUtils;
import com.g_draflab.orderit.Retrofit.ProductsServices;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRecyclerViewsAdapter extends RecyclerView.Adapter<ProductsHolder>{
    Context context;
    List<Product> products;




    public ProductRecyclerViewsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ProductsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_holder, viewGroup, false);

        return new ProductsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsHolder productsHolder, int i) {
        Product product = products.get(i);

        productsHolder.productName.setText(product.getName());
        productsHolder.productDescription.setText(product.getDescription());
        productsHolder.realPrice.setText(product.getPrice());
        productsHolder.discountedPrice.setText(product.getDiscountedPrice());

    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }

    public void setProducts(List<Product> products) {
        if(this.products != null){
            this.products.clear();
        }
        this.products = products;
        notifyDataSetChanged();
    }
}



class ProductsHolder extends RecyclerView.ViewHolder {

    TextView productName, productDescription, discountedPrice, realPrice;

    public ProductsHolder(@NonNull View itemView) {
        super(itemView);
        productName = itemView.findViewById(R.id.product_name_text_view);
        productDescription = itemView.findViewById(R.id.product_description_text_view);
        discountedPrice = itemView.findViewById(R.id.discounted_price);
        realPrice = itemView.findViewById(R.id.real_price_text_view);
    }
}
