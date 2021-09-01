package com.cashiar.mvp.acivity_bill_supplier_mvp;

import com.cashiar.models.AllBillCustomerReportModel;

public interface BillSuplierReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllBillCustomerReportModel allBillCustomerReportModel);
    void onDateSelected(String date,int i);

}
