package com.cashiar.mvp.activity_warehouse_products_mvp;

import com.cashiar.models.AllCustomersModel;
import com.cashiar.models.AllProductsModel;
import com.cashiar.models.SingleProductModel;
import com.cashiar.models.StockDataModel;
import com.cashiar.models.UserModel;

public interface WareHouseProductsActivityView {
    void onFinished();
    void onFailed(String msg);
    void onproductSuccess(AllProductsModel allProductsModel);

    void onProgressShow();
    void onProgressHide();






}
