package com.easycashiar.mvp.activity_add_product_mvp;

import com.easycashiar.models.AllCategoryModel;
import com.easycashiar.models.AllColorsModel;

public interface AddproductActivityView {
    void onFinished();

    void onLoad();

    void onFinishload();

    void onFailed(String msg);

    void onSuccess(AllCategoryModel allCategoryModel);

    void oncolorSuccess(AllColorsModel allColorsModel);
    void onSuccess();

}
