package com.easycashiar.mvp.activity_suppliers_mvp;

import com.easycashiar.models.AllCustomersModel;
import com.easycashiar.models.Slider_Model;
import com.easycashiar.models.UserModel;

import java.util.List;

public interface SuppliersActivityView {
    void onFinished();
    void onSuppliers();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllCustomersModel allCategoryModel);
    void onProgressSliderShow();
    void onProgressSliderHide();
    void onSliderSuccess(List<Slider_Model.Data> sliderModelList);
    void onLoad();

    void onFinishload();



    void onprofileload(UserModel body);

    void onSuccessDelete();
}
