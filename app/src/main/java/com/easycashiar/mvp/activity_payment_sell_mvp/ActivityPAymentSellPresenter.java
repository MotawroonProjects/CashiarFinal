package com.easycashiar.mvp.activity_payment_sell_mvp;

import android.content.Context;
import android.util.Log;

import com.easycashiar.R;
import com.easycashiar.models.AllCustomersModel;
import com.easycashiar.models.BillModel;
import com.easycashiar.models.CreateOrderModel;
import com.easycashiar.models.PaymentModel;
import com.easycashiar.models.UserModel;
import com.easycashiar.preferences.Preferences;
import com.easycashiar.remote.Api;
import com.easycashiar.tags.Tags;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPAymentSellPresenter {
    private UserModel userModel;
    private Preferences preferences;
    private PaymentSellActivityView view;
    private Context context;

    public ActivityPAymentSellPresenter(PaymentSellActivityView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void backPress() {

        view.onFinished();


    }



    public void getcustomer(UserModel userModel) {
        view.onLoad();

        Api.getService(Tags.base_url)
                .getCustomer("Bearer " + userModel.getToken())
                .enqueue(new Callback<AllCustomersModel>() {
                    @Override
                    public void onResponse(Call<AllCustomersModel> call, Response<AllCustomersModel> response) {
                        view.onFinishload();
                        if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                            view.ondcustomerSuccess(response.body());
                        } else {
                            view.onFinishload();
                            view.onFailed(context.getResources().getString(R.string.something));
                            try {
                                Log.e("error_code", response.code() + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<AllCustomersModel> call, Throwable t) {
                        try {
                            view.onFinishload();
                            view.onFailed(context.getString(R.string.something));
                            Log.e("Error", t.getMessage());
                        } catch (Exception e) {

                        }
                    }
                });
    }
    public void checkData(PaymentModel paymentModel, CreateOrderModel createOrderModel, UserModel userModel) {
        if (paymentModel.isDataValid(context)) {
            Create_order(userModel, createOrderModel);
        }
    }
    public void Create_order(UserModel userModel, CreateOrderModel createOrderModel) {
        view.onLoad();

        Api.getService(Tags.base_url)
                .createOrdersale("Bearer " + userModel.getToken(), createOrderModel)
                .enqueue(new Callback<BillModel>() {
                    @Override
                    public void onResponse(Call<BillModel> call, Response<BillModel> response) {
                        view.onFinishload();
                        if (response.isSuccessful() && response.body() != null) {

                            view.onsucess(response.body());

                        } else {
                            view.onFinishload();
                            view.onFailed(context.getResources().getString(R.string.something));
                            try {
                                Log.e("error_code", response.code() + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<BillModel> call, Throwable t) {
                        try {
                            view.onFinishload();
                            view.onFailed(context.getString(R.string.something));
                            Log.e("Error", t.getMessage());
                        } catch (Exception e) {

                        }
                    }
                });
    }

    public void addCustomers() {
        view.onCustomers();
    }

    public void getprofile(UserModel userModel) {
        //   Log.e("tjtjtj",singleDoctorModel.getId()+"  "+user_id);
        view.onLoad();

        Api.getService(Tags.base_url)
                .getprofile("Bearer " + userModel.getToken())
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
                                Log.e("error_codess", response.code() + response.errorBody().string());
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
