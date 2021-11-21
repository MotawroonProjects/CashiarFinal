package com.cashiar.mvp.activity_new_bill_of_purchases_mvp;

import com.cashiar.models.AllCustomersModel;
import com.cashiar.models.AllDiscountsModel;
import com.cashiar.models.AllProductsModel;
import com.cashiar.models.BillModel;
import com.cashiar.models.SingleProductModel;
import com.cashiar.models.StockDataModel;
import com.cashiar.models.UserModel;

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
