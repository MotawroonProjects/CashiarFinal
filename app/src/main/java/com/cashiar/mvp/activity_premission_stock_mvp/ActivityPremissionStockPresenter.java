package com.cashiar.mvp.activity_premission_stock_mvp;

import android.content.Context;
import android.util.Log;

import com.cashiar.R;
import com.cashiar.models.AllCustomersModel;
import com.cashiar.models.Slider_Model;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.activity_customers_mvp.PremissionStockActivityView;
import com.cashiar.preferences.Preferences;
import com.cashiar.remote.Api;
import com.cashiar.tags.Tags;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPremissionStockPresenter {
    private UserModel userModel;
    private Preferences preferences;
    private PremissionStockActivityView view;
    private Context context;

    public ActivityPremissionStockPresenter(PremissionStockActivityView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void backPress() {

        view.onFinished();


    }

    public void getCustomers(UserModel userModel,String Query)
    {
        // Log.e("tjtjtj",userModel.getIs_confirmed());
        view.onProgressShow();

        Api.getService(Tags.base_url)
                .getCustomer("Bearer "+userModel.getToken(),Query)
                .enqueue(new Callback<AllCustomersModel>() {
                    @Override
                    public void onResponse(Call<AllCustomersModel> call, Response<AllCustomersModel> response) {
                        view.onProgressHide();
                        if (response.isSuccessful() && response.body() != null) {
                            view.onSuccess(response.body());
                        } else {
                            view.onProgressHide();
                            view.onFailed(context.getString(R.string.something));
                            try {
                                Log.e("error_codess",response.code()+ response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<AllCustomersModel> call, Throwable t) {
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
