package com.cashiar.models;

import java.io.Serializable;
import java.util.List;

public class AllBillCustomerReportModel implements Serializable {
    private List<CustomerBillReportsModel> data;

    private int status;

    public List<CustomerBillReportsModel> getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }
}
