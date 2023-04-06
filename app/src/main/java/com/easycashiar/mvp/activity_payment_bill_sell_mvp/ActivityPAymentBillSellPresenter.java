package com.easycashiar.mvp.activity_payment_bill_sell_mvp;

import android.content.Context;
import android.util.Log;

import com.easycashiar.R;
import com.easycashiar.models.CreateOrderModel;
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

public class ActivityPAymentBillSellPresenter {
    private UserModel userModel;
    private Preferences preferences;
    private PaymentBillSellActivityView view;
    private Context context;

    public ActivityPAymentBillSellPresenter(PaymentBillSellActivityView view, Context context) {
        this.view = view;
        this.context = context;
    }
    public void backPress() {

            view.onFinished();


    }
    public void checkData(PaymentModel paymentModel, CreateOrderModel createOrderModel, UserModel userModel)
    {
        if (paymentModel.isDataValid(context)){
            Create_order(userModel,createOrderModel);
        }
    }

    public void Create_order(UserModel userModel, CreateOrderModel createOrderModel)
    {
        view.onLoad();

        Api.getService(Tags.base_url)
                .createBacksale("Bearer "+userModel.getToken(),createOrderModel)
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

}
