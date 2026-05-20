package com.example.entity;

public class BeerOrder {
    private int orderId;
    private int customerId;
    private String customerName;
    private String beerName;
    private int quantity;

    public BeerOrder() {
    }

    public BeerOrder(int orderId, int customerId, String customerName, String beerName, int quantity) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.beerName = beerName;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
