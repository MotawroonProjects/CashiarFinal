package com.cashiar.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoreBalanceModel implements Serializable {
    private String warehouse_id;
    private String product_id;
    @SerializedName("amount_of_stock")
    private String amount;
    private String store_name;
    private String product_name;
    private boolean isSelected = false;

    public StoreBalanceModel(String warehouse_id, String product_id, String amount, String store_name, String product_name) {
        this.warehouse_id = warehouse_id;
        this.product_id = product_id;
        this.amount = amount;
        this.store_name = store_name;
        this.product_name = product_name;
    }

    public String getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
