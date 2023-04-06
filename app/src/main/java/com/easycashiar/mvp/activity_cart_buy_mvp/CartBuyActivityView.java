package com.easycashiar.mvp.activity_cart_buy_mvp;

import com.easycashiar.models.AllDiscountsModel;
import com.easycashiar.models.UserModel;

public interface CartBuyActivityView {
    void onFinished();
    void onopenpay();
    void onFailed(String msg);
    void onServer();
    void onLoad();
    void onFinishload();
    void ondiscountSuccess(AllDiscountsModel allDiscountsModel);

    void onprofileload(UserModel body);

}
