package com.easycashiar.mvp.activity_payment_sell_mvp;

import com.easycashiar.models.AllCustomersModel;
import com.easycashiar.models.BillModel;
import com.easycashiar.models.UserModel;

public interface PaymentSellActivityView {
    void onFinished();

    void onFailed(String msg);
    void onLoad();
    void onFinishload();
    void ondcustomerSuccess(AllCustomersModel allCustomersModel);
    void onCustomers();
void onsucess(BillModel body);

    void onprofileload(UserModel body);
}
