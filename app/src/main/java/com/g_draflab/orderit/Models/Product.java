package com.g_draflab.orderit.Models;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("product_id")
    int productId;
    String name, description, price, thumbnail;
    @SerializedName("discounted_price")
    String discountedPrice;

    public Product() {
    }

    public Product(int productId, String name, String description, String price, String thumbnail, String discountedPrice) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.thumbnail = thumbnail;
        this.discountedPrice = discountedPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(String discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
}
