package com.cashiar.mvp.activity_stocks_mvp;

import android.content.Context;
import android.util.Log;

import com.cashiar.R;
import com.cashiar.models.AllCategoryModel;
import com.cashiar.models.Slider_Model;
import com.cashiar.models.StockDataModel;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.activity_categories_mvp.CategoriesActivityView;
import com.cashiar.preferences.Preferences;
import com.cashiar.remote.Api;
import com.cashiar.tags.Tags;

import java.io.IOException;

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


}
