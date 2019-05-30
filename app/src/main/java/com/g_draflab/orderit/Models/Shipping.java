package com.g_draflab.orderit.Models;

import com.google.gson.annotations.SerializedName;

public class Shipping {
    @SerializedName("shipping_region_id")
    int ShippingRegionId;
    @SerializedName("shipping_region")
    String shippingRegion;

    public Shipping(int shippingRegionId, String shippingRegion) {
        ShippingRegionId = shippingRegionId;
        this.shippingRegion = shippingRegion;
    }

    public Shipping() {
    }

    public int getShippingRegionId() {
        return ShippingRegionId;
    }

    public void setShippingRegionId(int shippingRegionId) {
        ShippingRegionId = shippingRegionId;
    }

    public String getShippingRegion() {
        return shippingRegion;
    }

    public void setShippingRegion(String shippingRegion) {
        this.shippingRegion = shippingRegion;
    }
}
