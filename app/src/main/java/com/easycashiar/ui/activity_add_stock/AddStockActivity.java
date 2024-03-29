package com.easycashiar.ui.activity_add_stock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.easycashiar.R;
import com.easycashiar.databinding.ActivityAddDepartmentBinding;
import com.easycashiar.databinding.ActivityAddStockBinding;
import com.easycashiar.language.Language;
import com.easycashiar.models.AddStockModel;
import com.easycashiar.models.StockModel;
import com.easycashiar.mvp.add_stock_activity_mvp.AddStockActivityView;
import com.easycashiar.mvp.add_stock_activity_mvp.AddStockPresenter;

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
            binding.tv.setText(getString(R.string.update));
            binding.btnConfirm.setText(getString(R.string.update));
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