package com.easycashiar.mvp.activity_welcome_mvp;


import com.easycashiar.models.UserModel;

public interface ActivityWelcomeCodeView {

    void onFailed();
    void onServer();
    void onLoad();
    void onFinishload();
    void onnotconnect(String msg);
    void accepted(UserModel userModel);


}
