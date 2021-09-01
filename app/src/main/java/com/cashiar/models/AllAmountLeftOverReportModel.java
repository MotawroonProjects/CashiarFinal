package com.cashiar.models;

import java.io.Serializable;
import java.util.List;

public class AllAmountLeftOverReportModel implements Serializable {
    private List<AmountLeftOverReportsModel> data;

    private int status;

    public List<AmountLeftOverReportsModel> getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }
}
