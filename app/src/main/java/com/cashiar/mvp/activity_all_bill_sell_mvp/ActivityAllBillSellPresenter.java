package com.cashiar.mvp.activity_all_bill_sell_mvp;

import android.content.Context;
import android.util.Log;

import com.cashiar.R;
import com.cashiar.models.AllBillOfSellModel;
import com.cashiar.models.UserModel;
import com.cashiar.preferences.Preferences;
import com.cashiar.remote.Api;
import com.cashiar.tags.Tags;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAllBillSellPresenter {
    private UserModel userModel;
    private Preferences preferences;
    private AllBillSellActivityView view;
    private Context context;

    public ActivityAllBillSellPresenter(AllBillSellActivityView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void backPress() {

        view.onFinished();


    }



    public void getbillSell(UserModel userModel)
    {
        view.onProgressShow();

        Api.getService(Tags.base_url)
                .getallbillsell("Bearer "+userModel.getToken())
                .enqueue(new Callback<AllBillOfSellModel>() {
                    @Override
                    public void onResponse(Call<AllBillOfSellModel> call, Response<AllBillOfSellModel> response) {
                        view.onProgressHide();
                        if (response.isSuccessful() && response.body() != null) {
                            view.onSuccess(response.body());
                        } else {
                            view.onProgressHide();
                            view.onFailed(context.getString(R.string.something));
                            try {
                                Log.e("error_code",response.code()+  response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<AllBillOfSellModel> call, Throwable t) {
                        try {
                            view.onProgressHide();
                            view.onFailed(context.getString(R.string.something));
                            Log.e("Error", t.getMessage());
                        } catch (Exception e) {

                        }
                    }
                });
    }
    public void getprofile(UserModel userModel)
    {
        //   Log.e("tjtjtj",singleDoctorModel.getId()+"  "+user_id);
        view.onLoad();

        Api.getService(Tags.base_url)
                .getprofile("Bearer "+userModel.getToken())
                .enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        view.onFinishload();

                        if (response.isSuccessful() && response.body() != null) {
                            view.onprofileload(response.body());
                        } else {
                            view.onFinishload();
                            view.onFailed(context.getString(R.string.something));
                            try {
                                Log.e("error_codess",response.code()+ response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        try {
                            view.onFinishload();
                            view.onFailed(context.getString(R.string.something));
                            Log.e("Error", t.getMessage());
                        } catch (Exception e) {

                        }
                    }
                });
    }
}
