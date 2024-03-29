package com.easycashiar.mvp.activity_pay_buy_mvp;

import android.content.Context;
import android.util.Log;

import com.easycashiar.R;
import com.easycashiar.models.AllCustomersModel;
import com.easycashiar.models.CreateBuyOrderModel;
import com.easycashiar.models.PaymentModel;
import com.easycashiar.models.UserModel;
import com.easycashiar.preferences.Preferences;
import com.easycashiar.remote.Api;
import com.easycashiar.tags.Tags;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPaymentBuyPresenter {
    private UserModel userModel;
    private Preferences preferences;
    private PaymentBuyActivityView view;
    private Context context;

    public ActivityPaymentBuyPresenter(PaymentBuyActivityView view, Context context) {
        this.view = view;
        this.context = context;
    }
    public void backPress() {

            view.onFinished();


    }

    public void getcustomer(UserModel userModel)
    {
        view.onLoad();

        Api.getService(Tags.base_url)
                .getsuppliers("Bearer "+userModel.getToken())
                .enqueue(new Callback<AllCustomersModel>() {
                    @Override
                    public void onResponse(Call<AllCustomersModel> call, Response<AllCustomersModel> response) {
                        view.onFinishload();
                        if (response.isSuccessful() && response.body() != null&&response.body().getData()!=null) {
                            view.ondcustomerSuccess(response.body());
                        } else {
                            view.onFinishload();
                            view.onFailed(context.getResources().getString(R.string.something));
                            try {
                                Log.e("error_code",response.code()+  response.errorBody().string());
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
    public void checkData(PaymentModel paymentModel, CreateBuyOrderModel createOrderModel, UserModel userModel)
    {
        if (paymentModel.isDataValid(context)){
            Create_order(userModel,createOrderModel);
        }
    }
    public void Create_order(UserModel userModel,CreateBuyOrderModel createOrderModel)
    {
        view.onLoad();

        Api.getService(Tags.base_url)
                .createOrderBuy("Bearer "+userModel.getToken(),createOrderModel)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        view.onFinishload();
                        if (response.isSuccessful() && response.body() != null) {
                            //  view.ondcustomerSuccess(response.body());
                            view.onsucess();
                        } else {
                            view.onFinishload();
                            view.onFailed(context.getResources().getString(R.string.something));
                            try {
                                Log.e("error_code",response.code()+  response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
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

}
