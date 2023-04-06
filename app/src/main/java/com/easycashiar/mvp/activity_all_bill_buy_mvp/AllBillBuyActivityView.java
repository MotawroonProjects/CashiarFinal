package com.easycashiar.mvp.activity_all_bill_buy_mvp;

import com.easycashiar.models.AllBillOfSellModel;
import com.easycashiar.models.UserModel;


public interface AllBillBuyActivityView {
    void onFinished();

    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllBillOfSellModel allBillOfSellModel);
    void onLoad();

    void onFinishload();



    void onprofileload(UserModel body);
}
