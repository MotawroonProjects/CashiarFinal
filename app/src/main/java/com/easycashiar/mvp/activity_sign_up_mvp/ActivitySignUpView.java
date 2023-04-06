package com.easycashiar.mvp.activity_sign_up_mvp;


import com.easycashiar.models.UserModel;

public interface ActivitySignUpView {
    void onSignupValid(UserModel userModel);
    void onFailed();
    void onServer();
    void onLoad();
    void onFinishload();
    void onnotconnect(String msg);

}
