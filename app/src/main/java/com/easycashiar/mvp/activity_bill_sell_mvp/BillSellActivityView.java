package com.easycashiar.mvp.activity_bill_sell_mvp;

import com.easycashiar.models.BillModel;
import com.easycashiar.models.UserModel;

public interface BillSellActivityView {
    void onFinished();

    void onFailed(String msg);
    void onLoad();
    void onFinishload();
    void onCustomers();
void onsucess();

    void onprofileload(UserModel body);

    void onSuccess(BillModel body);
}
