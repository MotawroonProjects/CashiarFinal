package com.easycashiar.mvp.fragmentsales_mvp;

import com.easycashiar.models.SettingModel;

public interface SalesFragmentView {
    void onFailed(String msg);

    void onLoad();

    void onFinishload();




    void onpurchase(SettingModel body);

    void onDateSelected(String date,int i);
}
