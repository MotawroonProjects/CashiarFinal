package com.cashiar.mvp.activity_aggreate_purchases_report_mvp;

import com.cashiar.models.AllSalesPurshReportModel;

public interface AggreatePurchasesReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllSalesPurshReportModel allSalesPurshReportModel);
    void onDateSelected(String date,int i);

}
