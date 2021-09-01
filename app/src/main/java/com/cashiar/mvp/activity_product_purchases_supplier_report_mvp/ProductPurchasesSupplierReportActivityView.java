package com.cashiar.mvp.activity_product_purchases_supplier_report_mvp;

import com.cashiar.models.AllSalesPurshReportModel;

public interface ProductPurchasesSupplierReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllSalesPurshReportModel allSalesPurshReportModel);
    void onDateSelected(String date,int i);

}
