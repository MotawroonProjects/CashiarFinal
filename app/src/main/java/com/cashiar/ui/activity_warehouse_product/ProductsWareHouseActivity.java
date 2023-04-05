package com.cashiar.ui.activity_warehouse_product;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cashiar.R;
import com.cashiar.adapters.DelteproductSwipe;
import com.cashiar.adapters.ProductsAdapter;
import com.cashiar.adapters.ProductsWareHouseAdapter;
import com.cashiar.adapters.SpinnerCategoryAdapter;
import com.cashiar.databinding.ActivityProductsBinding;
import com.cashiar.databinding.ActivityProductsWareHouseBinding;
import com.cashiar.language.Language;
import com.cashiar.models.AllCategoryModel;
import com.cashiar.models.AllProductsModel;
import com.cashiar.models.SingleCategoryModel;
import com.cashiar.models.SingleProductModel;
import com.cashiar.models.StockModel;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.activity_products_mvp.ProductsActivityView;
import com.cashiar.mvp.activity_warehouse_products_mvp.ActivityWareHouseProductsPresenter;
import com.cashiar.mvp.activity_warehouse_products_mvp.WareHouseProductsActivityView;
import com.cashiar.preferences.Preferences;
import com.cashiar.share.Common;
import com.cashiar.ui.activity_add_product.AddProductActivity;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class ProductsWareHouseActivity extends AppCompatActivity implements WareHouseProductsActivityView {
    private ActivityProductsWareHouseBinding binding;
    private ActivityWareHouseProductsPresenter presenter;
    private String lang;
    private ProductsWareHouseAdapter productsAdapter;
    private List<SingleProductModel> singleProductModels;
    private StockModel stockModel;

    private UserModel userModel;
    private Preferences preferences;

    private UserModel body;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            stockModel = (StockModel) intent.getSerializableExtra("data");

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_products_ware_house);
       getDataFromIntent();
        initView();
    }


    private void initView() {
        binding.setTitle(stockModel.getAr_title());
        Paper.init(this);

        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        lang = Paper.book().read("lang", "ar");
        singleProductModels = new ArrayList<>();
        binding.setLang(lang);
        binding.llBack.setOnClickListener(view -> {
            finish();
        });

        presenter = new ActivityWareHouseProductsPresenter(this, this);
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        productsAdapter = new ProductsWareHouseAdapter(this, singleProductModels);

        binding.recView.setAdapter(productsAdapter);
      presenter.getproducts(userModel,stockModel.getId()+"",null);

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
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onproductSuccess(AllProductsModel allProductsModel) {
        if (allProductsModel.getData() == null || allProductsModel.getData().size() == 0) {
            binding.llNoData.setVisibility(View.VISIBLE);
        } else {
            binding.llNoData.setVisibility(View.GONE);

        }
        singleProductModels.clear();
        singleProductModels.addAll(allProductsModel.getData());


        productsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {

            presenter.getproducts(userModel, stockModel.getId()+"", null);
        }
    }


    @Override
    public void onProgressShow() {
        binding.progBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProgressHide() {
        binding.progBar.setVisibility(View.GONE);

    }



}