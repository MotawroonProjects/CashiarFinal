package com.easycashiar.mvp.activity_unpaid_purchases_invoices_mvp;

import com.easycashiar.models.AllPurhcasesBillReportModel;

public interface UnpaidPurchasesInvoicesReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllPurhcasesBillReportModel allPurhcasesBillReportModel);
    void onDateSelected(String date,int i);

}
