package com.cashiar.mvp.activty_bill_purchases_report_mvp;

import com.cashiar.models.AllPurhcasesBillReportModel;
import com.cashiar.models.AllSalesBillReportModel;

public interface PurchasesBillReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllPurhcasesBillReportModel allPurhcasesBillReportModel);
    void onDateSelected(String date,int i);

}
