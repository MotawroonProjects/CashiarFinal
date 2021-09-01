package com.cashiar.ui.activity_bill_customer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cashiar.R;
import com.cashiar.adapters.CustomerBillReportAdapter;
import com.cashiar.databinding.ActivityCustomerBillBinding;
import com.cashiar.language.Language;
import com.cashiar.models.AllBillCustomerReportModel;
import com.cashiar.models.CustomerBillReportsModel;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.acivity_bill_customer_mvp.ActivityBillCustomerReportPresenter;
import com.cashiar.mvp.acivity_bill_customer_mvp.BillCustomerReportActivityView;
import com.cashiar.preferences.Preferences;
import com.cashiar.share.Common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class BillCustomerReportActivity extends AppCompatActivity implements BillCustomerReportActivityView {
    private ActivityCustomerBillBinding binding;
    private ActivityBillCustomerReportPresenter presenter;
    private String lang;

    private String query="all";
    private Preferences preferences;
    private UserModel userModel;
    private CustomerBillReportAdapter customerBillReportAdapter;
    private List<CustomerBillReportsModel> customerBillReportsModelList;
    private ProgressDialog dialog2;
    private String str = "all", end = "all";
    private int pos;
    private String type;
    private String type1, type2;
    private String name;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_customer_bill);
        getDataFromIntent();
        initView();
    }
    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            str = intent.getStringExtra("str");
            end = intent.getStringExtra("end");
            name = intent.getStringExtra("name");

        }
    }


    private void initView() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        customerBillReportsModelList = new ArrayList<>();
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);
        presenter = new ActivityBillCustomerReportPresenter(this, this);
        customerBillReportAdapter = new CustomerBillReportAdapter(this, customerBillReportsModelList);
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setAdapter(customerBillReportAdapter);
        binding.edtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                query = binding.edtSearch.getText().toString();
                if (!TextUtils.isEmpty(query)) {
                    Common.CloseKeyBoard(BillCustomerReportActivity.this, binding.edtSearch);
                    presenter.getreports(userModel, query, str, end,name);
                    return false;
                }
            }
            return false;
        });
        binding.llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.fl.setOnClickListener(view -> openSheet());
        binding.tvday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvday.setText(getResources().getString(R.string.day));
                closeSheet();
                type = "today";
                String date = dateFormat.format(new Date(System.currentTimeMillis()));
                str = date;
                end = date;
                Log.e("ldlldl", str + " " + end);

                if (pos == 0) {
                    type1 =getResources().getString(R.string.day);
                } else {
                    type2 = getResources().getString(R.string.day);
                }
                presenter.getreports(userModel,query,str,end,name);

            }
        });
        binding.tvthismonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(getResources().getString(R.string.this_month));
                closeSheet();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(System.currentTimeMillis()));
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
                str = dateFormat.format(calendar.getTime());
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                end = dateFormat.format(calendar.getTime());
                Log.e("ldlldl", str + " " + end);
                type = "this_month";

                if (pos == 0) {
                    type1 = getResources().getString(R.string.this_month);
                } else {
                    type2 = getResources().getString(R.string.this_month);
                }
                presenter.getreports(userModel,query,str,end,name);

            }
        });
        binding.tvlsevday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(getResources().getString(R.string.last_seven_day));
                closeSheet();
                type = "last7days";
                String date = dateFormat.format(new Date(System.currentTimeMillis()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(System.currentTimeMillis()));
                calendar.add(Calendar.DATE, -6);
                str = dateFormat.format(calendar.getTime());
                end = date;
                Log.e("ldlldl", str + " " + end);

                if (pos == 0) {
                    type1 = getResources().getString(R.string.last_seven_day);
                } else {
                    type2 = getResources().getString(R.string.last_seven_day);
                }
                presenter.getreports(userModel,query,str,end,name);

            }
        });
        binding.tvextentofwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(getResources().getString(R.string.extent_of_work));
                closeSheet();
                type = "all";
                str = "all";
                end = "all";
                if (pos == 0) {
                    type1 = getResources().getString(R.string.extent_of_work);
                } else {
                    type2 = getResources().getString(R.string.extent_of_work);
                }
                presenter.getreports(userModel,query,str,end,name);

            }
        });
        binding.tvlmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(getResources().getString(R.string.last_month));
                closeSheet();
                type = "last_month";
                String date = dateFormat.format(new Date(System.currentTimeMillis()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(System.currentTimeMillis()));
                calendar.add(Calendar.DATE, -29);
                str = dateFormat.format(calendar.getTime());
                end = date;
                Log.e("ldlldl", str + " " + end);
                if (pos == 0) {
                    type1 = getResources().getString(R.string.last_month);
                } else {
                    type2 = getResources().getString(R.string.last_month);
                }
                presenter.getreports(userModel,query,str,end,name);

            }
        });
        binding.tvlthrityday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(getResources().getString(R.string.last_thirty_day));
                closeSheet();
                type = "last30days";
                String date = dateFormat.format(new Date(System.currentTimeMillis()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(System.currentTimeMillis()));
                calendar.add(Calendar.DATE, -29);
                str = dateFormat.format(calendar.getTime());
                end = date;
                Log.e("ldlldl", str + " " + end);

                if (pos == 0) {
                    type1 = getResources().getString(R.string.last_thirty_day);
                } else {
                    type2 = getResources().getString(R.string.last_thirty_day);
                }
                presenter.getreports(userModel,query,str,end,name);


            }
        });
        binding.tvyday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(getResources().getString(R.string.yesterday));
                closeSheet();
                String date = dateFormat.format(new Date(System.currentTimeMillis()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(System.currentTimeMillis()));
                calendar.add(Calendar.DATE, -1);
                str = dateFormat.format(calendar.getTime());
                end = str;
                Log.e("ldlldl", str + " " + end);

                type = "yesterday";
                if (pos == 0) {
                    type1 = getResources().getString(R.string.yesterday);
                } else {
                    type2 = getResources().getString(R.string.yesterday);
                }
                presenter.getreports(userModel,query,str,end,name);


            }
        });
        binding.tvcustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(getResources().getString(R.string.custom_history));
                closeSheet();
                type = "custom";
                presenter.show(getFragmentManager(),1);

                if (pos == 0) {
                    type1 = getResources().getString(R.string.custom_history);
                } else {
                    type2 = getResources().getString(R.string.custom_history);
                }

            }
        });
        presenter.getreports(userModel, query, str, end,name);

    }


    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        presenter.backPress();
    }


    @Override
    public void onFinished() {
        finish();
    }


    @Override
    public void onSuccess(AllBillCustomerReportModel model) {

        //Log.e("dlldldl",model.getData().size()+"");
        customerBillReportsModelList.clear();
        customerBillReportAdapter.notifyDataSetChanged();
        customerBillReportsModelList.addAll(model.getData());
        if (customerBillReportsModelList.size() == 0) {
            binding.tvNoData.setVisibility(View.VISIBLE);
        } else {
            binding.tvNoData.setVisibility(View.GONE);
        }
        customerBillReportAdapter.notifyDataSetChanged();

    }


    @Override
    public void onProgressShow() {
        binding.progBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProgressHide() {
        binding.progBar.setVisibility(View.GONE);

    }
    private void openSheet() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_up);


        binding.flfilter.clearAnimation();
        binding.flfilter.startAnimation(animation);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.flfilter.setVisibility(View.VISIBLE);


            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void closeSheet() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);


        binding.flfilter.clearAnimation();
        binding.flfilter.startAnimation(animation);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                binding.flfilter.setVisibility(View.GONE);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    @Override
    public void onDateSelected(String date, int type) {
        if(type==1){
            str=date;
            presenter.show(getFragmentManager(),2);
        }
        else {
            end=date;
            presenter.getreports(userModel,query,str,end,name);

        }

    }

}