package com.easycashiar.mvp.activity_new_bill_of_purchases_mvp;

import com.easycashiar.models.AllCustomersModel;
import com.easycashiar.models.AllProductsModel;
import com.easycashiar.models.SingleProductModel;
import com.easycashiar.models.StockDataModel;
import com.easycashiar.models.UserModel;

public interface NewBillOfPurchasesActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onproductSuccess(AllProductsModel allProductsModel);
    void onLoad();
    void onFinishload();

    void onSuccessDelete();

    void onprofileload(UserModel body);

    void onproductSuccess(SingleProductModel body);


    void ondSupplierSuccess(AllCustomersModel body);

    void onSupplier();

    void onsucess();

    void onSuccess(StockDataModel body);
}
