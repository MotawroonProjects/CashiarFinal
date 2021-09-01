package com.cashiar.models;

import java.io.Serializable;
import java.util.List;

public class UnpaidBillSaleReportModel implements Serializable {
    private SalesBillReportsModel data;
    private int status;

    public SalesBillReportsModel getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }
}
