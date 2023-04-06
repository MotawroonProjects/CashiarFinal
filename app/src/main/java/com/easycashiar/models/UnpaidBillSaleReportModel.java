package com.easycashiar.models;

import java.io.Serializable;

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
