package com.easycashiar.mvp.activity_aggreate_sales_report_mvp;

import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.easycashiar.R;
import com.easycashiar.models.AllSalesPurshReportModel;
import com.easycashiar.models.UserModel;
import com.easycashiar.preferences.Preferences;
import com.easycashiar.remote.Api;
import com.easycashiar.tags.Tags;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAggreateSalesReportPresenter implements DatePickerDialog.OnDateSetListener{
    private UserModel userModel;
    private Preferences preferences;
    private AggreateSalesReportActivityView view;
    private Context context;
    private DatePickerDialog datePickerDialog, datePickerDialog2;
    private int type = 0;
    public ActivityAggreateSalesReportPresenter(AggreateSalesReportActivityView view, Context context) {
        this.view = view;
        this.context = context;
        createDateDialog();
        createDateDialog2();
    }

    private void createDateDialog() {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setOkText(context.getString(R.string.select));
        datePickerDialog.setCancelText(context.getString(R.string.cancel));
        datePickerDialog.setAccentColor(ContextCompat.getColor(context, R.color.colorPrimary));
        datePickerDialog.setOkColor(ContextCompat.getColor(context, R.color.colorPrimary));
        datePickerDialog.setCancelColor(ContextCompat.getColor(context, R.color.gray4));
        datePickerDialog.setLocale(Locale.ENGLISH);
        datePickerDialog.setVersion(DatePickerDialog.Version.VERSION_1);

    }

    private void createDateDialog2() {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        datePickerDialog2 = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog2.setOkText(context.getString(R.string.select));
        datePickerDialog2.setCancelText(context.getString(R.string.cancel));
        datePickerDialog2.setAccentColor(ContextCompat.getColor(context, R.color.colorPrimary));
        datePickerDialog2.setOkColor(ContextCompat.getColor(context, R.color.colorPrimary));
        datePickerDialog2.setCancelColor(ContextCompat.getColor(context, R.color.gray4));
        datePickerDialog2.setLocale(Locale.ENGLISH);
        datePickerDialog2.setVersion(DatePickerDialog.Version.VERSION_1);

    }

    public void show(FragmentManager fragmentManager, int i) {
        type = i;
        try {
            if(type==1) {
                datePickerDialog.show(fragmentManager, "");
            }
            else {
                datePickerDialog2.show(fragmentManager, "");


            }} catch (Exception e) {
            Log.e("lslslls",e.toString());

        }
    }



    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.MONTH, monthOfYear);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String date = dateFormat.format(new Date(calendar.getTimeInMillis()));
        ActivityAggreateSalesReportPresenter.this.view.onDateSelected(date, type);
    }
    public void backPress() {

        view.onFinished();


    }


    public void getreports(UserModel userModel,String Query,String str,String end)
    {
        // Log.e("tjtjtj",userModel.getIs_confirmed());
        view.onProgressShow();

        Api.getService(Tags.base_url)
                .getAggreatesalesReport("Bearer "+userModel.getToken(),userModel.getId()+"",Query,str,end)
                .enqueue(new Callback<AllSalesPurshReportModel>() {
                    @Override
                    public void onResponse(Call<AllSalesPurshReportModel> call, Response<AllSalesPurshReportModel> response) {
                        view.onProgressHide();
                        if (response.isSuccessful() && response.body() != null) {
                            if (response.body() != null && response.body().getStatus() == 200 && response.body().getData() != null) {

                                view.onSuccess(response.body());}
                        } else {
                            view.onProgressHide();
                            view.onFailed(context.getString(R.string.something));
                            try {
                                Log.e("error_codess",response.code()+ response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onFailure(Call<AllSalesPurshReportModel> call, Throwable t) {
                        try {
                            view.onProgressHide();
                            view.onFailed(context.getString(R.string.something));
                            Log.e("Error", t.getMessage());
                        } catch (Exception e) {

                        }
                    }
                });
    }

}
