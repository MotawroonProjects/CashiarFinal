package com.easycashiar.models;

import java.io.Serializable;
import java.util.List;

public class SalesBillReportsModel implements Serializable {
    private int id;
    private String sale_type;
    private int user_id;
    private int client_id;
    private int creditor_id;
    private int debtor_id;
    private double total_price;
    private double paid_price;
    private double remaining_price;
    private double discount_value;
    private String date;
    private int added_by_id;
    private String deleted_at;
    private String created_at;
    private String updated_at;
    private SingleCustomerSuplliersModel client;
    private List<SalesPurchReportsModel> sale_details;
    private SingleCustomerSuplliersModel one_client;

    public int getId() {
        return id;
    }

    public String getSale_type() {
        return sale_type;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getClient_id() {
        return client_id;
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

    public double getDiscount_value() {
        return discount_value;
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

    public SingleCustomerSuplliersModel getClient() {
        return client;
    }

    public List<SalesPurchReportsModel> getSale_details() {
        return sale_details;
    }

    public SingleCustomerSuplliersModel getOne_client() {
        return one_client;
    }
}
