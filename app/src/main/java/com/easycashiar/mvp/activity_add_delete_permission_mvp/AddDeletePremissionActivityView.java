package com.easycashiar.mvp.activity_add_delete_permission_mvp;

import com.easycashiar.models.StockDataModel;
import com.easycashiar.models.UserDataModel;
import com.easycashiar.models.UserModel;

public interface AddDeletePremissionActivityView {
    void onFinished();

    void onFailed(String msg);


    void onLoad();

    void onFinishload();

    void onSuccess();
    void onProgressShow();
    void onProgressHide();

    void onpermisionSuccess(StockDataModel body);

    void onprofileload(UserModel body);

    void onSuccessUsers(UserDataModel body);
}
