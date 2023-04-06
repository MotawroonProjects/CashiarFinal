package com.easycashiar.mvp.activity_detailed_earning_Report_mvp;

import com.easycashiar.models.AllSalesPurshReportModel;

public interface DetailedEarningReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllSalesPurshReportModel allSalesPurshReportModel);
    void onDateSelected(String date,int i);

}
