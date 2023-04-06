package com.easycashiar.mvp.activity_add_expenses_mvp;

import com.easycashiar.models.AllAccountsModel;

public interface AddExpensesActivityView {
    void onFinished();

    void onFailed(String msg);

    void onSuccess(AllAccountsModel allAccountsModel);

    void onLoad();

    void onFinishload();

    void onSuccess();

    void onAccount();

    void onDateSelected(String date);

}
