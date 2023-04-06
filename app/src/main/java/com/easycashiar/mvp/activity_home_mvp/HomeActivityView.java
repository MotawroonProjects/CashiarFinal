package com.easycashiar.mvp.activity_home_mvp;

import com.easycashiar.models.SettingModel;
import com.easycashiar.models.UserModel;

public interface HomeActivityView {
    void onNavigateToLoginActivity();
    void onFinished();
    void onAddproducts();
    void onCustomers();
    void onPremissionStock();
    void onSuppliers();

    void onAddbillSell();
    void onAddbillBuy();
    void onExpenses();
    void share();
    void onLoad();

    void onFinishload();


    void logout();
    void onFailed(String msg);

    void onBacksale();

    void onBackbuy();

    void oncahier();

    void profile();

    void lang();

    void onprofileload(UserModel body);

    void reprot();

    void contactus();

    void subscriptions();
    void onContactVaild();
    void onFailed();
    void onServer();

    void onnotconnect(String msg);

    void ViewSocial(String link);

    void onsetting(SettingModel body);
}

