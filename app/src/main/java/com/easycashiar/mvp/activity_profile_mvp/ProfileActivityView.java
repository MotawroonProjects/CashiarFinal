package com.easycashiar.mvp.activity_profile_mvp;

import com.easycashiar.models.UserModel;

public interface ProfileActivityView {
    void onFinished();

    void onLoad();

    void onFinishload();

    void onFailed(String msg);


    void onprofileload(UserModel body);


    void subscriptions();

    void update();
}
