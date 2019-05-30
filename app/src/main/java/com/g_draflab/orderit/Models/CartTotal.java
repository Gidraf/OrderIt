package com.g_draflab.orderit.Models;

import com.google.gson.annotations.SerializedName;

public class CartTotal {
    @SerializedName("total_amount")
    String totalAmount;

    public CartTotal(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public CartTotal() {
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
