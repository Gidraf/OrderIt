package com.g_draflab.orderit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.g_draflab.orderit.Activities.ProductActivity;
import com.g_draflab.orderit.Interfaces.OnLoadMore;
import com.g_draflab.orderit.Models.Product;
import com.g_draflab.orderit.R;

import java.util.List;

public class ProductRecyclerViewsAdapter extends RecyclerView.Adapter<ProductsHolder>{
    Context context;
    List<Product> products;
    View.OnClickListener listener;


    public ProductRecyclerViewsAdapter(Context context, View.OnClickListener listener, RecyclerView recyclerView) {
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ProductsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_holder, viewGroup, false);
        return new ProductsHolder(view,listener );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsHolder productsHolder, int i) {
        final Product product = products.get(i);
        productsHolder.productName.setText(product.getName());
        productsHolder.productDescription.setText(product.getDescription());
        productsHolder.realPrice.setText("$"+product.getPrice());
        productsHolder.discountedPrice.setText("$"+product.getDiscountedPrice());
        productsHolder.realPrice.setPaintFlags(productsHolder.realPrice.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        productsHolder.setListener(v -> context.startActivity(new Intent(context, ProductActivity.class).putExtra("productId", product.getProductId())));
    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }

    public void setProducts(List<Product> products) {
            this.products = products;
            notifyDataSetChanged();
    }
}



class ProductsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView productName, productDescription, discountedPrice, realPrice;

    View.OnClickListener listener;

    public ProductsHolder(@NonNull View itemView, View.OnClickListener listener) {
        super(itemView);
        this.listener = listener;
        productName = itemView.findViewById(R.id.product_name_text_view);
        productDescription = itemView.findViewById(R.id.product_description_text_view);
        discountedPrice = itemView.findViewById(R.id.discounted_price);
        realPrice = itemView.findViewById(R.id.real_price_text_view);

        itemView.setOnClickListener(this);
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    public ProductsHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }
}
