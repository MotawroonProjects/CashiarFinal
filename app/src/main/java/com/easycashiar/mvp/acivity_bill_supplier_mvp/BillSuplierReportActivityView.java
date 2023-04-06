package com.easycashiar.mvp.acivity_bill_supplier_mvp;

import com.easycashiar.models.AllBillCustomerReportModel;

public interface BillSuplierReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllBillCustomerReportModel allBillCustomerReportModel);
    void onDateSelected(String date,int i);

}
