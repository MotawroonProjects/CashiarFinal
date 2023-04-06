package com.easycashiar.mvp.activity_pay_buy_mvp;

import com.easycashiar.models.AllCustomersModel;

public interface PaymentBuyActivityView {
    void onFinished();

    void onFailed(String msg);
    void onLoad();
    void onFinishload();
    void ondcustomerSuccess(AllCustomersModel allCustomersModel);
    void onCustomers();
    void onsucess();


}
