package com.easycashiar.mvp.activity_aggreate_earn_report_mvp;

import com.easycashiar.models.AggreateEarnReportsModel;

public interface AggreateEarningReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AggreateEarnReportsModel aggreateEarnReportsModel);
    void onDateSelected(String date,int i);

}
