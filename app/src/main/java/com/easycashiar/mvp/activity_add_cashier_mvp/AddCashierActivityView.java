package com.easycashiar.mvp.activity_add_cashier_mvp;

import com.easycashiar.models.AllPermissionModel;
import com.easycashiar.models.UserModel;

public interface AddCashierActivityView {
    void onFinished();

    void onFailed(String msg);


    void onLoad();

    void onFinishload();

    void onSuccess();
    void onProgressShow();
    void onProgressHide();

    void onpermisionSuccess(AllPermissionModel body);

    void onprofileload(UserModel body);
}
