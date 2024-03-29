package com.easycashiar.mvp.activity_products_mvp;

import com.easycashiar.models.AllCategoryModel;
import com.easycashiar.models.AllProductsModel;
import com.easycashiar.models.UserModel;

public interface ProductsActivityView {
    void onFinished();
    void onAddproducts();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllCategoryModel allCategoryModel);
    void onproductSuccess(AllProductsModel allProductsModel);
    void onLoad();
    void onFinishload();

    void onSuccessDelete();

    void onprofileload(UserModel body);
}
