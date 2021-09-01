package com.cashiar.mvp.acivity_bill_customer_mvp;

import com.cashiar.models.AllAmountLeftOverReportModel;
import com.cashiar.models.AllBillCustomerReportModel;

public interface BillCustomerReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllBillCustomerReportModel allBillCustomerReportModel);
    void onDateSelected(String date,int i);

}
