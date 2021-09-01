package com.cashiar.mvp.activity_new_bill_of_sell_mvp;

import android.content.Context;
import android.util.Log;

import com.cashiar.R;
import com.cashiar.models.AllCategoryModel;
import com.cashiar.models.AllCustomersModel;
import com.cashiar.models.AllDiscountsModel;
import com.cashiar.models.AllProductsModel;
import com.cashiar.models.BillModel;
import com.cashiar.models.CreateOrderModel;
import com.cashiar.models.PaymentModel;
import com.cashiar.models.SingleProductModel;
import com.cashiar.models.UserModel;
import com.cashiar.preferences.Preferences;
import com.cashiar.remote.Api;
import com.cashiar.tags.Tags;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityNewSellOfSellPresenter {
    private UserModel userModel;
    private Preferences preferences;
    private NewSellOfSellActivityView view;
    private Context context;

    public ActivityNewSellOfSellPresenter(NewSellOfSellActivityView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void backPress() {

        view.onFinished();


    }


    public void getproducts(UserModel userModel,String cat,String query)
    {

        Api.getService(Tags.base_url)
                .getproducts("Bearer "+userModel.getToken(),query,cat)
                .enqueue(new Callback<AllProductsModel>() {
                    @Override
                    public void onResponse(Call<AllProductsModel> call, Response<AllProductsModel> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            view.onproductSuccess(response.body());
                        } else {
                            view.onFailed(context.getString(R.string.something));
                            try {
                                Log.e("error_code",response.code()+  response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<AllProductsModel> call, Throwable t) {
                        try {
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
    public void getsingleproduct(UserModel userModel,String barcode)
    {
        view.onProgressShow();

        Api.getService(Tags.base_url)
                .getsingleproductbybarcode("Bearer "+userModel.getToken(),barcode)
                .enqueue(new Callback<SingleProductModel>() {
                    @Override
                    public void onResponse(Call<SingleProductModel> call, Response<SingleProductModel> response) {
                        view.onProgressHide();
                        if (response.isSuccessful() && response.body() != null) {
                            view.onproductSuccess(response.body());
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
                    public void onFailure(Call<SingleProductModel> call, Throwable t) {
                        try {
                            view.onProgressHide();
                            view.onFailed(context.getString(R.string.something));
                            Log.e("Error", t.getMessage());
                        } catch (Exception e) {

                        }
                    }
                });
    }

    public void getdiscount(UserModel userModel)
    {
        view.onLoad();

        Api.getService(Tags.base_url)
                .getDiscount("Bearer "+userModel.getToken())
                .enqueue(new Callback<AllDiscountsModel>() {
                    @Override
                    public void onResponse(Call<AllDiscountsModel> call, Response<AllDiscountsModel> response) {
                        view.onFinishload();
                        if (response.isSuccessful() && response.body() != null&&response.body().getData()!=null) {
                            view.ondiscountSuccess(response.body());
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
                    public void onFailure(Call<AllDiscountsModel> call, Throwable t) {
                        try {
                            view.onFinishload();
                            view.onFailed(context.getString(R.string.something));
                            Log.e("Error", t.getMessage());
                        } catch (Exception e) {

                        }
                    }
                });
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

    public void addCustomers() {
        view.onCustomers();
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

}
