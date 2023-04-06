package com.easycashiar.mvp.activity_premission_stock_mvp;

import com.easycashiar.models.Slider_Model;
import com.easycashiar.models.UserDataModel;
import com.easycashiar.models.UserModel;

import java.util.List;

public interface PremissionStockActivityView {
    void onFinished();
    void onCustomers();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(UserDataModel userDataModel);
    void onProgressSliderShow();
    void onProgressSliderHide();
    void onSliderSuccess(List<Slider_Model.Data> sliderModelList);
    void onLoad();

    void onFinishload();



    void onprofileload(UserModel body);

    void onSuccessDelete();
}
