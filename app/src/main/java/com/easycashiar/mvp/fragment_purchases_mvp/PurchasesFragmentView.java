package com.easycashiar.mvp.fragment_purchases_mvp;

import com.easycashiar.models.SettingModel;

public interface PurchasesFragmentView {
    void onFailed(String msg);

    void onLoad();

    void onFinishload();




    void onpurchase(SettingModel body);

    void onDateSelected(String date,int i);
}
