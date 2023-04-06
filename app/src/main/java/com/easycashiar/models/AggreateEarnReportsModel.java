package com.easycashiar.models;

import java.io.Serializable;

public class AggreateEarnReportsModel implements Serializable {
    private int status;
    private Data data;

    public int getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }

    public class Data implements Serializable {
        private double purchase;
        private double sale;
        private double profits;

        public double getPurchase() {
            return purchase;
        }

        public double getSale() {
            return sale;
        }

        public double getProfits() {
            return profits;
        }
    }

}
