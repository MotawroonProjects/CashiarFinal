package com.easycashiar.mvp.activity_cart_bill_buy_mvp;

import com.easycashiar.models.UserModel;

public interface CartBillBuyActivityView {
    void onFinished();

    void onopenpay();

    void onLoad();

    void onFinishload();

    void onFailed(String msg);


    void onprofileload(UserModel body);

}
