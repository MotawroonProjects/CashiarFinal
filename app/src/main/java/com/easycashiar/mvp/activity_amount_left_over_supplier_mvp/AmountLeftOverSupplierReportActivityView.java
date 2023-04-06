package com.easycashiar.mvp.activity_amount_left_over_supplier_mvp;

import com.easycashiar.models.AllAmountLeftOverReportModel;

public interface AmountLeftOverSupplierReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllAmountLeftOverReportModel allAmountLeftOverReportModel);
    void onDateSelected(String date,int i);

}
