package com.g_draflab.orderit.Models;

import com.google.gson.annotations.SerializedName;

public class CustomerRegisterResponse {
    @SerializedName("customer")
    Customer customer;
    @SerializedName("accessToken")
    String accessToken;
    @SerializedName("expires_in")
    String expires_in;

    public CustomerRegisterResponse() {
    }

    public CustomerRegisterResponse(Customer customer, String accessToken, String expires_in) {
        this.customer = customer;
        this.accessToken = accessToken;
        this.expires_in = expires_in;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
