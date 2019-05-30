package com.g_draflab.orderit.Models;

import com.google.gson.annotations.SerializedName;

public class CartResponse {
    @SerializedName("item_id")
    int itemId;
    @SerializedName("name")
    String itemName;
    String attributes;
    String price;
    int quantity;
    @SerializedName("roduct_id")
    int productId;
    String subtotal;

    public CartResponse() {
    }

    public CartResponse(int itemId, String itemName, String attributes, String price, int quantity, int productId, String subtotal) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.attributes = attributes;
        this.price = price;
        this.quantity = quantity;
        this.productId = productId;
        this.subtotal = subtotal;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
}
