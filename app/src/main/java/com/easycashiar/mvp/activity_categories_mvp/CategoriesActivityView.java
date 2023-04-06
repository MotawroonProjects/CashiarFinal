package com.easycashiar.mvp.activity_categories_mvp;

import com.easycashiar.models.AllCategoryModel;
import com.easycashiar.models.Slider_Model;

import java.util.List;

public interface CategoriesActivityView {
    void onFinished();
    void onAddDepartment();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllCategoryModel allCategoryModel);
    void onLoad();
    void onFinishload();

    void onSuccessDelete();
    void onProgressSliderShow();
    void onProgressSliderHide();
    void onSliderSuccess(List<Slider_Model.Data> sliderModelList);
}
