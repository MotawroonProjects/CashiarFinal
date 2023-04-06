package com.easycashiar.mvp.activity_discount_mvp;

import com.easycashiar.models.AllDiscountsModel;
import com.easycashiar.models.UserModel;

public interface DiscountActivityView {
    void onFinished();
    void onAddDiscount();
    void onFailed(String msg);

    void onProgressShow();
    void onProgressHide();
    void ondiscountSuccess(AllDiscountsModel allDiscountsModel);
    void onLoad();

    void onFinishload();



    void onprofileload(UserModel body);

    void onSuccessDelete();
}
