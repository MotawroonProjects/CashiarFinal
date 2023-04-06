package com.easycashiar.mvp.stock_balance_activity_mvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.easycashiar.R;
import com.easycashiar.models.AllProductsModel;
import com.easycashiar.models.StockDataModel;
import com.easycashiar.models.StoreBalanceDataModel;
import com.easycashiar.models.UserModel;
import com.easycashiar.preferences.Preferences;
import com.easycashiar.remote.Api;
import com.easycashiar.share.Common;
import com.easycashiar.tags.Tags;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityStockBalancePresenter {
    private UserModel userModel;
    private Preferences preferences;
    private StocksBalanceActivityView view;
    private Context context;


    public ActivityStockBalancePresenter(StocksBalanceActivityView view, Context context) {
        this.view = view;
        this.context = context;
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(context);
    }


    public void getStocks() {
        Api.getService(Tags.base_url)
                .getStocks("Bearer " + userModel.getToken())
                .enqueue(new Callback<StockDataModel>() {
                    @Override
                    public void onResponse(Call<StockDataModel> call, Response<StockDataModel> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            view.onStocksSuccess(response.body().getData());
                        } else {
                            view.onFailed(context.getString(R.string.failed));
                        }


                    }

                    @Override
                    public void onFailure(Call<StockDataModel> call, Throwable t) {
                        try {
                            view.onFailed(context.getString(R.string.failed));
                            Log.e("Error", t.getMessage());
                        } catch (Exception e) {

                        }
                    }
                });
    }

    public void getProducts() {
        Api.getService(Tags.base_url)
                .getproducts("Bearer " + userModel.getToken(), null, "all")
                .enqueue(new Callback<AllProductsModel>() {
                    @Override
                    public void onResponse(Call<AllProductsModel> call, Response<AllProductsModel> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            view.onProductSuccess(response.body().getData());
                        } else {
                            view.onFailed(context.getString(R.string.failed));
                        }


                    }

                    @Override
                    public void onFailure(Call<AllProductsModel> call, Throwable t) {
                        try {
                            view.onFailed(context.getString(R.string.failed));
                            Log.e("Error", t.getMessage());
                        } catch (Exception e) {

                        }
                    }
                });
    }

    public void addData(StoreBalanceDataModel model) {
        ProgressDialog dialog = Common.createProgressDialog(context, context.getString(R.string.wait));
        dialog.show();

        Api.getService(Tags.base_url)
                .addStoreBalance("Bearer " + userModel.getToken(), model)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        dialog.dismiss();
                        if (response.isSuccessful() && response.body() != null) {
                            view.onAddSuccess();
                        } else {
                            view.onFailed(context.getString(R.string.failed));
                        }


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        try {
                            view.onFailed(context.getString(R.string.failed));
                            Log.e("Error", t.getMessage());
                        } catch (Exception e) {

                        }
                    }
                });
    }


}
