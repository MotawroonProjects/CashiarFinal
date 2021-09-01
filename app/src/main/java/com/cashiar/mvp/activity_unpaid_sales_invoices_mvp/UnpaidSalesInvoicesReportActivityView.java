package com.cashiar.mvp.activity_unpaid_sales_invoices_mvp;

import com.cashiar.models.AllSalesBillReportModel;
import com.cashiar.models.AllSalesPurshReportModel;
import com.cashiar.models.UnpaidBillSaleReportModel;

public interface UnpaidSalesInvoicesReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllSalesBillReportModel allSalesBillReportModel);
    void onDateSelected(String date,int i);

    void onSuccess(UnpaidBillSaleReportModel body);
}
