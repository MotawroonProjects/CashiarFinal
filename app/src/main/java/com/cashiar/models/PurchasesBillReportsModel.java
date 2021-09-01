package com.cashiar.models;

import java.io.Serializable;

public class PurchasesBillReportsModel implements Serializable {
    private int id;
    private String purchase_type;
    private int user_id;
    private int supplier_id;
    private int creditor_id;
    private int debtor_id;
    private double total_price;
    private double paid_price;
    private double remaining_price;
    private String date;
    private int added_by_id;
    private String deleted_at;
    private String created_at;
    private String updated_at;
    private SingleCustomerSuplliersModel supplier;

    public int getId() {
        return id;
    }


    public int getUser_id() {
        return user_id;
    }

    public String getPurchase_type() {
        return purchase_type;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public SingleCustomerSuplliersModel getSupplier() {
        return supplier;
    }

    public int getCreditor_id() {
        return creditor_id;
    }

    public int getDebtor_id() {
        return debtor_id;
    }

    public double getTotal_price() {
        return total_price;
    }

    public double getPaid_price() {
        return paid_price;
    }

    public double getRemaining_price() {
        return remaining_price;
    }


    public String getDate() {
        return date;
    }

    public int getAdded_by_id() {
        return added_by_id;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

}
