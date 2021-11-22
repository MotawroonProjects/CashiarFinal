package com.cashiar.mvp.activity_add_delete_permission_mvp;

import com.cashiar.models.AllPermissionModel;
import com.cashiar.models.StockDataModel;
import com.cashiar.models.UserDataModel;
import com.cashiar.models.UserModel;

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
