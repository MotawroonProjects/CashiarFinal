package com.cashiar.ui.activity_add_stock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cashiar.R;
import com.cashiar.adapters.ColorsAdapter;
import com.cashiar.databinding.ActivityAddDepartmentBinding;
import com.cashiar.databinding.ActivityAddStockBinding;
import com.cashiar.language.Language;
import com.cashiar.models.AddDepartmentModel;
import com.cashiar.models.AddStockModel;
import com.cashiar.models.AllColorsModel;
import com.cashiar.models.SingleCategoryModel;
import com.cashiar.models.SingleColorModel;
import com.cashiar.models.StockModel;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.activity_add_department_mvp.ActivityAddDepartmentPresenter;
import com.cashiar.mvp.add_stock_activity_mvp.AddStockActivityView;
import com.cashiar.mvp.add_stock_activity_mvp.AddStockPresenter;
import com.cashiar.preferences.Preferences;
import com.cashiar.share.Common;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class AddStockActivity extends AppCompatActivity implements AddStockActivityView {
    private ActivityAddStockBinding binding;
    private AddStockPresenter presenter;
    private String lang;
    private AddStockModel addStockModel;
    private StockModel model;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_stock);
        getDataFromIntent();
        initView();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        model = (StockModel) intent.getSerializableExtra("data");

    }


    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);

        addStockModel = new AddStockModel();
        if (model != null) {
            addStockModel.setId(model.getId() + "");
            addStockModel.setName(model.getAr_title());
        }
        binding.setModel(addStockModel);

        binding.llBack.setOnClickListener(view -> {
            finish();
        });
        presenter = new AddStockPresenter(this, this);
        binding.btnConfirm.setOnClickListener(v -> {
            presenter.checkData(addStockModel);
        });
    }


    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        setResult(RESULT_OK);
        finish();
    }
}