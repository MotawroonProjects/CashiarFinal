package com.easycashiar.mvp.activity_discount_mvp;

import android.content.Context;
import android.util.Log;

import com.easycashiar.R;
import com.easycashiar.models.AllDiscountsModel;
import com.easycashiar.models.UserModel;
import com.easycashiar.preferences.Preferences;
import com.easycashiar.remote.Api;
import com.easycashiar.tags.Tags;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDiscountPresenter {
    private UserModel userModel;
    private Preferences preferences;
    private DiscountActivityView view;
    private Context context;

    public ActivityDiscountPresenter(DiscountActivityView view, Context context) {
        this.view = view;
        this.context = context;
    }
    public void backPress() {

        view.onFinished();


    }

    public void addDiscount() {
        view.onAddDiscount();
    }
    public void getdiscount(UserModel userModel)
    {
        view.onProgressShow();

        Api.getService(Tags.base_url)
                .getDiscount("Bearer "+userModel.getToken())
                .enqueue(new Callback<AllDiscountsModel>() {
                    @Override
                    public void onResponse(Call<AllDiscountsModel> call, Response<AllDiscountsModel> response) {
                         view.onProgressHide();
                        if (response.isSuccessful() && response.body() != null&&response.body().getData()!=null) {
                            view.ondiscountSuccess(response.body());
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
                    public void onFailure(Call<AllDiscountsModel> call, Throwable t) {
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

    public void deletedicount(int id, UserModel userModel) {
        view.onLoad();
        Api.getService(Tags.base_url)
                .deltediscount("Bearer " + userModel.getToken(), id+"")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        view.onFinishload();
                        if (response.isSuccessful() && response.body() != null) {
                            //  Log.e("eeeeee", response.body().getUser().getName());
                            // view.onUserFound(response.body());
                            view.onSuccessDelete();
                        } else {
                            try {
                                Log.e("mmmmmmmmmm", response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            if (response.code() == 500) {
                                // view.onServer();
                            } else {
                                view.onFailed(response.message());
                                //  Toast.makeText(VerificationCodeActivity.this, getString(R.string.failed), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        try {
                            view.onFinishload();
                            if (t.getMessage() != null) {
                                Log.e("msg_category_error", t.getMessage() + "__");

                                if (t.getMessage().toLowerCase().contains("failed to connect") || t.getMessage().toLowerCase().contains("unable to resolve host")) {
                                    //   view.onnotconnect(t.getMessage().toLowerCase());
                                    //  Toast.makeText(VerificationCodeActivity.this, getString(R.string.something), Toast.LENGTH_SHORT).show();
                                } else {
                                    view.onFailed(t.getMessage());
                                    // Toast.makeText(VerificationCodeActivity.this, getString(R.string.failed), Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception e) {
                            Log.e("Error", e.getMessage() + "__");
                        }
                    }
                });
    }
}
