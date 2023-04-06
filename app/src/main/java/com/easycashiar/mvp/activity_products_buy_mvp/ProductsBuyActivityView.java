package com.easycashiar.mvp.activity_products_buy_mvp;

import com.easycashiar.models.AllCategoryModel;
import com.easycashiar.models.AllProductsModel;
import com.easycashiar.models.SingleProductModel;
import com.easycashiar.models.UserModel;

public interface ProductsBuyActivityView {
    void onFinished();
    void onCart();

    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllCategoryModel allCategoryModel);
    void onproductSuccess(AllProductsModel allProductsModel);
    void onproductSuccess(SingleProductModel allProductsModel);

    void onLoad();
    void onFinishload();


    void onprofileload(UserModel body);
}
