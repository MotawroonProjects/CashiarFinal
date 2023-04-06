package com.easycashiar.mvp.activity_warehouse_products_mvp;

import com.easycashiar.models.AllProductsModel;

public interface WareHouseProductsActivityView {
    void onFinished();
    void onFailed(String msg);
    void onproductSuccess(AllProductsModel allProductsModel);

    void onProgressShow();
    void onProgressHide();






}
