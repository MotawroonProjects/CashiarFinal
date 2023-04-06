package com.easycashiar.mvp.activity_all_bill_sell_mvp;

import com.easycashiar.models.AllBillOfSellModel;
import com.easycashiar.models.UserModel;


public interface AllBillSellActivityView {
    void onFinished();

    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllBillOfSellModel allBillOfSellModel);
    void onLoad();

    void onFinishload();



    void onprofileload(UserModel body);
}
