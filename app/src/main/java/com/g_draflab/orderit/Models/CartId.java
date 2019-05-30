package com.g_draflab.orderit.Models;

import com.google.gson.annotations.SerializedName;

public class CartId {
    @SerializedName("cart_id")
    String cartId;

    public CartId(String cartId) {
        this.cartId = cartId;
    }

    public CartId() {
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
}
