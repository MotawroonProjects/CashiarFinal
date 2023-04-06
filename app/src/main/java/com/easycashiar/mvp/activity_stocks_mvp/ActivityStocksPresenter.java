package com.easycashiar.mvp.activity_stocks_mvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.easycashiar.R;
import com.easycashiar.models.StockDataModel;
import com.easycashiar.models.UserModel;
import com.easycashiar.preferences.Preferences;
import com.easycashiar.remote.Api;
import com.easycashiar.share.Common;
import com.easycashiar.tags.Tags;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityStocksPresenter {
    private UserModel userModel;
    private Preferences preferences;
    private StocksActivityView view;
    private Context context;


    public ActivityStocksPresenter(StocksActivityView view, Context context) {
        this.view = view;
        this.context = context;
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(context);
    }


    public void getData() {
        view.onProgressShow();
        Api.getService(Tags.base_url)
                .getStocks("Bearer " + userModel.getToken())
                .enqueue(new Callback<StockDataModel>() {
                    @Override
                    public void onResponse(Call<StockDataModel> call, Response<StockDataModel> response) {
                        view.onProgressHide();
                        if (response.isSuccessful() && response.body() != null) {
                            view.onSuccess(response.body().getData());
                        } else {
                            view.onFailed(context.getString(R.string.failed));
                        }


                    }

                    @Override
                    public void onFailure(Call<StockDataModel> call, Throwable t) {
                        try {
                            view.onProgressHide();
                            view.onFailed(context.getString(R.string.failed));
                            Log.e("Error", t.getMessage());
                        } catch (Exception e) {

                        }
                    }
                });
    }

    public void delete(String stock_id, int pos) {
        ProgressDialog dialog = Common.createProgressDialog(context, context.getString(R.string.wait));
        dialog.show();
        Api.getService(Tags.base_url)
                .deleteStock("Bearer " + userModel.getToken(), stock_id)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        dialog.dismiss();
                        if (response.isSuccessful() && response.body() != null) {
                            view.onDeleteSuccess(pos);
                        } else {
                            view.onDeleteFailed(context.getString(R.string.failed), pos);
                        }


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        try {
                            dialog.dismiss();
                            view.onDeleteFailed(context.getString(R.string.failed), pos);
                            Log.e("Error", t.getMessage());
                        } catch (Exception e) {

                        }
                    }
                });
    }


}
