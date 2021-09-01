package com.cashiar.mvp.activty_bill_sales_report_mvp;

import com.cashiar.models.AllSalesBillReportModel;

public interface SalesBillReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllSalesBillReportModel allSalesBillReportModel);
    void onDateSelected(String date,int i);

}
