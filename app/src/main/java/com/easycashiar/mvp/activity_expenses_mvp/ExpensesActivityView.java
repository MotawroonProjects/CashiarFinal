package com.easycashiar.mvp.activity_expenses_mvp;

import com.easycashiar.models.AllExpensesModel;
import com.easycashiar.models.Slider_Model;
import com.easycashiar.models.UserModel;

import java.util.List;

public interface ExpensesActivityView {
    void onFinished();
    void onExpenses();

    void onProgressShow();
    void onProgressHide();
    void onexpensesSuccess(AllExpensesModel allExpensesModel);
    void onProgressSliderShow();
    void onProgressSliderHide();
    void onSliderSuccess(List<Slider_Model.Data> sliderModelList);
    void onLoad();

    void onFinishload();

    void onFailed(String msg);


    void onprofileload(UserModel body);

    void onSuccessDelete();
}
