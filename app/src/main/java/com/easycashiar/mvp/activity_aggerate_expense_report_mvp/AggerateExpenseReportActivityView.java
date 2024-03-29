package com.easycashiar.mvp.activity_aggerate_expense_report_mvp;

import com.easycashiar.models.AllExpensesModel;

public interface AggerateExpenseReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllExpensesModel allExpensesModel);
    void onDateSelected(String date,int i);

}
