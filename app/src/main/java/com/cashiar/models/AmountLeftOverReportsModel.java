package com.cashiar.models;

import java.io.Serializable;

public class AmountLeftOverReportsModel implements Serializable {

    private SingleCustomerSuplliersModel client;
    private SingleCustomerSuplliersModel supplier;

    private double remaining_price;
    private double client_id;
    private double total_remaining_price;

    public SingleCustomerSuplliersModel getClient() {
        return client;
    }

    public SingleCustomerSuplliersModel getSupplier() {
        return supplier;
    }

    public double getRemaining_price() {
        return remaining_price;
    }

    public double getClient_id() {
        return client_id;
    }

    public double getTotal_remaining_price() {
        return total_remaining_price;
    }
}
