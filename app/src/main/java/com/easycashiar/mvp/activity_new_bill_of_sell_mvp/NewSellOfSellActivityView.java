package com.easycashiar.mvp.activity_new_bill_of_sell_mvp;

import com.easycashiar.models.AllCustomersModel;
import com.easycashiar.models.AllDiscountsModel;
import com.easycashiar.models.AllProductsModel;
import com.easycashiar.models.BillModel;
import com.easycashiar.models.SingleProductModel;
import com.easycashiar.models.StockDataModel;
import com.easycashiar.models.UserModel;

public interface NewSellOfSellActivityView {
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

    void ondiscountSuccess(AllDiscountsModel body);

    void ondcustomerSuccess(AllCustomersModel body);

    void onCustomers();

    void onsucess(BillModel body);

    void onSuccess(StockDataModel body);
}
