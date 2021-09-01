package com.cashiar.mvp.activity_detialed_expense_report_mvp;

import com.cashiar.models.AllExpensesModel;
import com.cashiar.models.AllSalesPurshReportModel;

public interface DetailedExpenseReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllExpensesModel allExpensesModel);
    void onDateSelected(String date,int i);

}
