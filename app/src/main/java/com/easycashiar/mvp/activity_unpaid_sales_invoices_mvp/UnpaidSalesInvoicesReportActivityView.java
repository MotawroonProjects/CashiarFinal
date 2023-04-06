package com.easycashiar.mvp.activity_unpaid_sales_invoices_mvp;

import com.easycashiar.models.AllSalesBillReportModel;
import com.easycashiar.models.UnpaidBillSaleReportModel;

public interface UnpaidSalesInvoicesReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllSalesBillReportModel allSalesBillReportModel);
    void onDateSelected(String date,int i);

    void onSuccess(UnpaidBillSaleReportModel body);
}
