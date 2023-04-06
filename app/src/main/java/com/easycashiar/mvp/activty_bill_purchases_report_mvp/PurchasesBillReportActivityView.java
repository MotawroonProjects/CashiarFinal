package com.easycashiar.mvp.activty_bill_purchases_report_mvp;

import com.easycashiar.models.AllPurhcasesBillReportModel;

public interface PurchasesBillReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllPurhcasesBillReportModel allPurhcasesBillReportModel);
    void onDateSelected(String date,int i);

}
