package com.cashiar.mvp.activity_product_sold_customer_report_mvp;

import com.cashiar.models.AllSalesPurshReportModel;

public interface ProductSoldCustomerReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllSalesPurshReportModel allSalesPurshReportModel);
    void onDateSelected(String date,int i);

}
