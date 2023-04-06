package com.easycashiar.mvp.activity_warehouse_products_mvp;

import android.content.Context;
import android.util.Log;

import com.easycashiar.R;
import com.easycashiar.models.AllProductsModel;
import com.easycashiar.models.UserModel;
import com.easycashiar.preferences.Preferences;
import com.easycashiar.remote.Api;
import com.easycashiar.tags.Tags;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityWareHouseProductsPresenter {
    private UserModel userModel;
    private Preferences preferences;
    private WareHouseProductsActivityView view;
    private Context context;

    public ActivityWareHouseProductsPresenter(WareHouseProductsActivityView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void backPress() {

        view.onFinished();


    }


    public void getproducts(UserModel userModel,String stock,String query)
    {
        view.onProgressShow();
        Api.getService(Tags.base_url)
                .getproductsInStock("Bearer "+userModel.getToken(),query,stock)
                .enqueue(new Callback<AllProductsModel>() {
                    @Override
                    public void onResponse(Call<AllProductsModel> call, Response<AllProductsModel> response) {
                        view.onProgressHide();
                       if (response.isSuccessful() && response.body() != null) {
                            view.onproductSuccess(response.body());
                        } else {
                           // view.onFailed(context.getString(R.string.something));
                            try {
                                Log.e("error_code",response.code()+  response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<AllProductsModel> call, Throwable t) {
                        view.onProgressHide();

                        try {
                            view.onFailed(context.getString(R.string.something));
                            Log.e("Error", t.getMessage());
                        } catch (Exception e) {

                        }
                    }
                });
    }




}
