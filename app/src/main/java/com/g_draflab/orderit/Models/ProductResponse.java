package com.g_draflab.orderit.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResponse {
    int count;
    @SerializedName("rows")
    List<Product> productList;

    public ProductResponse(int count, List<Product> productList) {
        this.count = count;
        this.productList = productList;
    }

    public ProductResponse() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
