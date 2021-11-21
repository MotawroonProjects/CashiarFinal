package com.cashiar.ui.activity_stocks;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.cashiar.R;
import com.cashiar.adapters.CategoriesAdapter;
import com.cashiar.adapters.DelteDepartmentSwipe;
import com.cashiar.adapters.SliderAdapter;
import com.cashiar.databinding.ActivityCategoriesBinding;
import com.cashiar.databinding.ActivityStocksBinding;
import com.cashiar.language.Language;
import com.cashiar.models.AllCategoryModel;
import com.cashiar.models.SingleCategoryModel;
import com.cashiar.models.Slider_Model;
import com.cashiar.models.StockModel;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.activity_categories_mvp.ActivityCategoriesPresenter;
import com.cashiar.mvp.activity_stocks_mvp.ActivityStocksPresenter;
import com.cashiar.mvp.activity_stocks_mvp.StocksActivityView;
import com.cashiar.preferences.Preferences;
import com.cashiar.share.Common;
import com.cashiar.ui.activity_add_departmnet.AddDepartmnetActivity;
import com.cashiar.ui.activity_categories.CategoriesActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.paperdb.Paper;

public class StocksActivity extends AppCompatActivity implements StocksActivityView {
    private ActivityStocksBinding binding;
    private ActivityStocksPresenter presenter;
    private String lang;
    private List<StockModel> list;
    private CategoriesAdapter categoriesAdapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stocks);
        initView();
    }


    private void initView() {

        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);
        binding.llBack.setOnClickListener(view -> {
            finish();
        });
        presenter = new ActivityStocksPresenter(this, this);

        categoriesAdapter = new CategoriesAdapter(this, singleCategoryModelList);
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setAdapter(categoriesAdapter);
        ItemTouchHelper.SimpleCallback simpleCallback = new DelteDepartmentSwipe(this, 0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        ItemTouchHelper helper = new ItemTouchHelper(simpleCallback);
        helper.attachToRecyclerView(binding.recView);

    }

    @Override
    public void onSuccess(List<StockModel> list) {
        if (list.size()>0){
            this.list = list;
            binding.llNoData.setVisibility(View.GONE);
        }else {
            binding.llNoData.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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