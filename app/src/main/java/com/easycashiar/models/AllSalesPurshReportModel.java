package com.easycashiar.models;

import java.io.Serializable;
import java.util.List;

public class AllSalesPurshReportModel implements Serializable {
    private List<SalesPurchReportsModel> data;

    private int status;

    public List<SalesPurchReportsModel> getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }
}
