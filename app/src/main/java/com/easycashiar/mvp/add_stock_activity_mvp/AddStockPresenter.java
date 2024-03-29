package com.easycashiar.mvp.add_stock_activity_mvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.easycashiar.R;
import com.easycashiar.models.AddStockModel;
import com.easycashiar.models.UserModel;
import com.easycashiar.preferences.Preferences;
import com.easycashiar.remote.Api;
import com.easycashiar.share.Common;
import com.easycashiar.tags.Tags;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddStockPresenter {
    private UserModel userModel;
    private Preferences preferences;
    private AddStockActivityView view;
    private Context context;
    private ProgressDialog dialog;

    public AddStockPresenter(AddStockActivityView view, Context context) {
        this.view = view;
        this.context = context;
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(context);

    }

    public void checkData(AddStockModel addStockModel) {
        if (addStockModel.isDataValid(context)) {
            if (addStockModel.getId() == null) {
                addStock(addStockModel);

            } else {
                updateStock(addStockModel);

            }
        }
    }


    private void updateStock(AddStockModel addStockModel) {
        dialog = Common.createProgressDialog(context, context.getString(R.string.wait));
        dialog.show();

        Api.getService(Tags.base_url)
                .updateStock("Bearer " + userModel.getToken(), addStockModel.getId(), addStockModel.getName())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        dialog.dismiss();
                        if (response.isSuccessful() && response.body() != null) {
                            view.onSuccess();
                        } else {
                            try {
                                Log.e("mmmmmmmmmm", response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        try {
                            dialog.dismiss();

                            if (t.getMessage() != null) {
                            }
                        } catch (Exception e) {
                            Log.e("Error", e.getMessage() + "__");
                        }
                    }
                });

    }

    private void addStock(AddStockModel addStockModel) {
        dialog = Common.createProgressDialog(context, context.getString(R.string.wait));
        dialog.show();

        Api.getService(Tags.base_url)
                .addStock("Bearer " + userModel.getToken(), addStockModel.getName())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        dialog.dismiss();
                        if (response.isSuccessful() && response.body() != null) {
                            view.onSuccess();
                        } else {
                            try {
                                Log.e("mmmmmmmmmm", response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        try {
                            dialog.dismiss();

                            if (t.getMessage() != null) {
                            }
                        } catch (Exception e) {
                            Log.e("Error", e.getMessage() + "__");
                        }
                    }
                });

    }


}
