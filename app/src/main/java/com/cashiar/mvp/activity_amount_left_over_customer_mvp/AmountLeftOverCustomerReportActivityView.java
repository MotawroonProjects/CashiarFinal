package com.cashiar.mvp.activity_amount_left_over_customer_mvp;

import com.cashiar.models.AllAmountLeftOverReportModel;
import com.cashiar.models.AllSalesPurshReportModel;

public interface AmountLeftOverCustomerReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllAmountLeftOverReportModel allAmountLeftOverReportModel);
    void onDateSelected(String date,int i);

}
