package com.cashiar.models;

import java.io.Serializable;
import java.util.List;

public class AllSalesBillReportModel implements Serializable {
    private List<SalesBillReportsModel> data;

    private int status;

    public List<SalesBillReportsModel> getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }
}
