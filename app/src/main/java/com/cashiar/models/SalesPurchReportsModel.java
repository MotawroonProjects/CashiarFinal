package com.cashiar.models;

import java.io.Serializable;

public class SalesPurchReportsModel implements Serializable {
    private int id;
    private int sale_id;
    private int product_id;
    private int purchase_id;
    private double price_value;
    private double amount;
    private String created_at;
    private String updated_at;
    private OneSale one_sale;
    private OneProduct one_product;
    private OneSale one_purchase;
    private OneProduct product;

    public int getId() {
        return id;
    }

    public int getSale_id() {
        return sale_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getPurchase_id() {
        return purchase_id;
    }

    public double getPrice_value() {
        return price_value;
    }

    public double getAmount() {
        return amount;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public OneSale getOne_sale() {
        return one_sale;
    }

    public OneProduct getOne_product() {
        return one_product;
    }

    public OneSale getOne_purchase() {
        return one_purchase;
    }

    public OneProduct getProduct() {
        return product;
    }

    public class OneSale implements Serializable {
        private int id;
        private String date;
        private double discount_value;

        public int getId() {
            return id;
        }

        public String getDate() {
            return date;
        }

        public double getDiscount_value() {
            return discount_value;
        }
    }

    public class OneProduct implements Serializable {
        private int id;
        private String title;
        private double product_cost;

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public double getProduct_cost() {
            return product_cost;
        }
    }

}
