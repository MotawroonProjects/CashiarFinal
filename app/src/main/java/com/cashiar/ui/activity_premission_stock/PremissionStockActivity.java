package com.cashiar.ui.activity_premission_stock;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cashiar.R;
import com.cashiar.adapters.UsersAdapter;
import com.cashiar.databinding.ActivityPremissionStockBinding;
import com.cashiar.language.Language;
import com.cashiar.models.Slider_Model;
import com.cashiar.models.UserDataModel;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.activity_customers_mvp.PremissionStockActivityView;
import com.cashiar.mvp.activity_premission_stock_mvp.ActivityPremissionStockPresenter;
import com.cashiar.preferences.Preferences;
import com.cashiar.share.Common;
import com.cashiar.ui.activity_add_Customer.AddCustomerActivity;
import com.cashiar.ui.activity_add_delete_premission.AddDeletePremissionActivity;

import java.util.ArrayList;
import java.util.List;

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
    private List<UserModel> userModelList;
    private UsersAdapter usersAdapter;
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
        userModelList = new ArrayList<>();
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);
        presenter = new ActivityPremissionStockPresenter(this, this);
        presenter.getprofile(userModel);
        usersAdapter = new UsersAdapter(this, userModelList);

        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setAdapter(usersAdapter);

        binding.llBack.setOnClickListener(view -> {
            finish();
        });


        presenter.getUsers(userModel);

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
    public void onSuccess(UserDataModel model) {

        //Log.e("dlldldl",model.getData().size()+"");
        if (model.getData() == null || model.getData().size() == 0) {
            binding.llNoNotification.setVisibility(View.VISIBLE);
        } else {
            binding.llNoNotification.setVisibility(View.GONE);

        }
        userModelList.clear();
        userModelList.addAll(model.getData());
        usersAdapter.notifyDataSetChanged();

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
    public void onProgressSliderShow() {
    }

    @Override
    public void onProgressSliderHide() {
    }

    @Override
    public void onSliderSuccess(List<Slider_Model.Data> sliderModelList) {


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
        userModelList.remove(pos);
        usersAdapter.notifyDataSetChanged();
    }


    public void onitemselect(UserModel userModel, String type) {
        Intent intent = new Intent(PremissionStockActivity.this, AddDeletePremissionActivity.class);
        intent.putExtra("data", userModel);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}