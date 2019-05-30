package com.g_draflab.orderit.Models;

public class OrderId {
    int orderId;

    public OrderId(int orderId) {
        this.orderId = orderId;
    }

    public OrderId() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
