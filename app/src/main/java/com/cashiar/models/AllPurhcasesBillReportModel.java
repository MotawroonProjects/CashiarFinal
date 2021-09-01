package com.cashiar.models;

import java.io.Serializable;
import java.util.List;

public class AllPurhcasesBillReportModel implements Serializable {
    private List<PurchasesBillReportsModel> data;

    private int status;

    public List<PurchasesBillReportsModel> getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }
}
