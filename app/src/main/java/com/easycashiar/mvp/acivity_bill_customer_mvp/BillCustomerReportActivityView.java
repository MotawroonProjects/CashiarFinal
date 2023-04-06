package com.easycashiar.mvp.acivity_bill_customer_mvp;

import com.easycashiar.models.AllBillCustomerReportModel;

public interface BillCustomerReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllBillCustomerReportModel allBillCustomerReportModel);
    void onDateSelected(String date,int i);

}
