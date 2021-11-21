package com.cashiar.ui.activity_premission_stock;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cashiar.R;
import com.cashiar.adapters.CustomerAdapter;
import com.cashiar.adapters.DelteCustomerSwipe;
import com.cashiar.adapters.SliderAdapter;
import com.cashiar.databinding.ActivityCustomersBinding;
import com.cashiar.databinding.ActivityPremissionStockBinding;
import com.cashiar.language.Language;
import com.cashiar.models.AllCustomersModel;
import com.cashiar.models.SingleCustomerSuplliersModel;
import com.cashiar.models.Slider_Model;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.activity_customers_mvp.PremissionStockActivityView;
import com.cashiar.mvp.activity_premission_stock_mvp.ActivityPremissionStockPresenter;
import com.cashiar.preferences.Preferences;
import com.cashiar.share.Common;
import com.cashiar.ui.activity_add_Customer.AddCustomerActivity;
import com.cashiar.ui.activity_add_delete_premission.AddDeletePremissionActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.paperdb.Paper;

public class PremissionStockActivity extends AppCompatActivity implements PremissionStockActivityView {
    private ActivityPremissionStockBinding binding;
    private ActivityPremissionStockPresenter presenter;
    private String lang;
    private float dX = 0, dY = 0;
    private float downRawX, downRawY;
    private String query;
    private Preferences preferences;
    private UserModel userModel;
    private List<SingleCustomerSuplliersModel> allCustomersModels;
    private CustomerAdapter customerAdapter;
    private ProgressDialog dialog2;
    private UserModel body;
    private int pos;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_premission_stock);
        initView();
    }


    private void initView() {
        allCustomersModels = new ArrayList<>();
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);
        presenter = new ActivityPremissionStockPresenter(this, this);
        presenter.getprofile(userModel);
        customerAdapter = new CustomerAdapter(this, allCustomersModels);

        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setAdapter(customerAdapter);

        binding.llBack.setOnClickListener(view -> {
            finish();
        });


        presenter.getCustomers(userModel, query);

    }


    @Override
    public void onCustomers() {
        Intent intent = new Intent(this, AddCustomerActivity.class);
        startActivityForResult(intent, 1);
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
    public void onSuccess(AllCustomersModel model) {

        //Log.e("dlldldl",model.getData().size()+"");
        if (model.getData() == null || model.getData().size() == 0) {
            binding.llNoNotification.setVisibility(View.VISIBLE);
        } else {
            binding.llNoNotification.setVisibility(View.GONE);

        }
        allCustomersModels.clear();
        allCustomersModels.addAll(model.getData());
        customerAdapter.notifyDataSetChanged();

    }

    @Override
    public void onProgressShow() {
        binding.progBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProgressHide() {
        binding.progBar.setVisibility(View.GONE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            query = "";
            presenter.getCustomers(userModel, query);
        }
    }

    @Override
    public void onProgressSliderShow() {
    }

    @Override
    public void onProgressSliderHide() {
    }

    @Override
    public void onSliderSuccess(List<Slider_Model.Data> sliderModelList) {


    }


    public void update(SingleCustomerSuplliersModel singleCustomerSuplliersModel) {
        Intent intent = new Intent(PremissionStockActivity.this, AddCustomerActivity.class);
        intent.putExtra("data", singleCustomerSuplliersModel);
        intent.putExtra("type", "update");
        startActivity(intent);
    }



    @Override
    public void onLoad() {
        if (dialog2 == null) {
            dialog2 = Common.createProgressDialog(this, getString(R.string.wait));
            dialog2.setCancelable(false);
        } else {
            dialog2.dismiss();
        }
        dialog2.show();
    }

    @Override
    public void onFinishload() {
        dialog2.dismiss();
    }


    @Override
    public void onprofileload(UserModel body) {
        this.body = body;
    }

    @Override
    public void onSuccessDelete() {
        allCustomersModels.remove(pos);
        customerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.getprofile(userModel);
        presenter.getCustomers(userModel,query);
    }

    public void onitemselect(SingleCustomerSuplliersModel singleCustomerSuplliersModel) {
        Intent intent = new Intent(PremissionStockActivity.this, AddDeletePremissionActivity.class);
        intent.putExtra("data", singleCustomerSuplliersModel);
        intent.putExtra("type", "update");
        startActivity(intent);
    }
}