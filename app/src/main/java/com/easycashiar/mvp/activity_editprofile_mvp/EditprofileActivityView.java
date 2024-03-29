package com.easycashiar.mvp.activity_editprofile_mvp;

import com.easycashiar.models.PlaceGeocodeData;
import com.easycashiar.models.UserModel;

public interface EditprofileActivityView {
    void onFinished();

    void onFailed(String msg);


    void onLoad();

    void onFinishload();

    void onSuccess(UserModel body);
    void AddMarker(double lat, double lng, String replace);

    void onaddress(PlaceGeocodeData body);

    void onprofileload(UserModel body);
}
