package com.cashiar.ui.activity_stock_balance;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cashiar.R;
import com.cashiar.adapters.StocksAdapter;
import com.cashiar.adapters.StocksSwipe;
import com.cashiar.adapters.StoreBalanceAdapter;
import com.cashiar.databinding.ActivityStockBalanceBinding;
import com.cashiar.databinding.ActivityStocksBinding;
import com.cashiar.language.Language;
import com.cashiar.models.StockModel;
import com.cashiar.models.StoreBalanceModel;
import com.cashiar.mvp.activity_stocks_mvp.ActivityStocksPresenter;
import com.cashiar.ui.activity_add_stock.AddStockActivity;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class StockBalanceActivity extends AppCompatActivity {
    private ActivityStockBalanceBinding binding;
    private String lang;
    private List<StoreBalanceModel> list;
    private StoreBalanceAdapter adapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stock_balance);
        initView();
    }


    private void initView() {
        list = new ArrayList<>();
        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);
        binding.llBack.setOnClickListener(view -> {
            finish();
        });

        adapter = new StoreBalanceAdapter(this, list);
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setAdapter(adapter);

    }


}