package com.cashiar.mvp.activity_unpaid_purchases_invoices_mvp;

import com.cashiar.models.AllPurhcasesBillReportModel;
import com.cashiar.models.AllSalesBillReportModel;

public interface UnpaidPurchasesInvoicesReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllPurhcasesBillReportModel allPurhcasesBillReportModel);
    void onDateSelected(String date,int i);

}
