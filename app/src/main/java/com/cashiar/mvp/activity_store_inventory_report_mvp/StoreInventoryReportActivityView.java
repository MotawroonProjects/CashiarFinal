package com.cashiar.mvp.activity_store_inventory_report_mvp;

import com.cashiar.models.AllSalesPurshReportModel;

public interface StoreInventoryReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllSalesPurshReportModel allSalesPurshReportModel);
    void onDateSelected(String date,int i);

}
