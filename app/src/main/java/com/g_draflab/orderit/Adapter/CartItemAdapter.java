package com.g_draflab.orderit.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.g_draflab.orderit.Models.CartResponse;
import com.g_draflab.orderit.R;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartViewHolder> {
    List<CartResponse> cartResponses;
    Context context;


    public void setCartResponses(List<CartResponse> cartResponses) {
        this.cartResponses = cartResponses;
        notifyDataSetChanged();
    }

    public CartItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_content_holder, viewGroup, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i) {
        CartResponse cartResponse =  cartResponses.get(i);
        cartViewHolder.cartItemName.setText(cartResponse.getItemName());
        cartViewHolder.cartItemRealPrice.setText(cartResponse.getPrice());
        cartViewHolder.attributesText.setText(cartResponse.getAttributes());
        cartViewHolder.discountedPrice.setText(String.valueOf(cartResponse.getQuantity())+ " items");
    }

    @Override
    public int getItemCount() {
        return cartResponses == null ? 0 : cartResponses.size();
    }
}

class CartViewHolder extends RecyclerView.ViewHolder {
    TextView cartItemName, cartItemRealPrice, attributesText, discountedPrice;
    ImageView editItem;
    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        editItem = itemView.findViewById(R.id.cart_edit_item);
        cartItemName = itemView.findViewById(R.id.cart_item_name);
        cartItemRealPrice = itemView.findViewById(R.id.item_real_price);
        attributesText = itemView.findViewById(R.id.item_size);
        discountedPrice = itemView.findViewById(R.id.item_discounted_price);
    }
}
