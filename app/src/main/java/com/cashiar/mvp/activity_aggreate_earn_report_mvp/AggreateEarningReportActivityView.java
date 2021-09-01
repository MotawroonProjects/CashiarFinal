package com.cashiar.mvp.activity_aggreate_earn_report_mvp;

import com.cashiar.models.AggreateEarnReportsModel;
import com.cashiar.models.AllSalesPurshReportModel;

public interface AggreateEarningReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AggreateEarnReportsModel aggreateEarnReportsModel);
    void onDateSelected(String date,int i);

}
