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
import android.widget.AdapterView;
import android.widget.Toast;

import com.cashiar.R;
import com.cashiar.adapters.SpinnerProductAdapter;
import com.cashiar.adapters.SpinnerStockAdapter;
import com.cashiar.adapters.StocksAdapter;
import com.cashiar.adapters.StocksSwipe;
import com.cashiar.adapters.StoreBalanceAdapter;
import com.cashiar.databinding.ActivityStockBalanceBinding;
import com.cashiar.databinding.ActivityStocksBinding;
import com.cashiar.language.Language;
import com.cashiar.models.SingleProductModel;
import com.cashiar.models.StockModel;
import com.cashiar.models.StoreBalanceModel;
import com.cashiar.mvp.activity_stocks_mvp.ActivityStocksPresenter;
import com.cashiar.mvp.stock_balance_activity_mvp.ActivityStockBalancePresenter;
import com.cashiar.mvp.stock_balance_activity_mvp.StocksBalanceActivityView;
import com.cashiar.ui.activity_add_stock.AddStockActivity;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class StockBalanceActivity extends AppCompatActivity implements StocksBalanceActivityView {
    private ActivityStockBalanceBinding binding;
    private String lang;
    private List<StoreBalanceModel> list;
    private StoreBalanceAdapter adapter;
    private List<StockModel> stockModelList;
    private SpinnerStockAdapter spinnerStockAdapter;
    private ActivityStockBalancePresenter  presenter;
    private List<SingleProductModel> productModelList;
    private SpinnerProductAdapter spinnerProductAdapter;
    private StockModel stockModel;
    private SingleProductModel productModel;

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
        stockModelList = new ArrayList<>();
        productModelList = new ArrayList<>();
        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);

        presenter = new ActivityStockBalancePresenter(this,this);

        binding.llBack.setOnClickListener(view -> {
            finish();
        });

        adapter = new StoreBalanceAdapter(this, list);
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setAdapter(adapter);

        spinnerStockAdapter = new SpinnerStockAdapter(stockModelList, this);
        binding.spinnerStores.setAdapter(spinnerStockAdapter);

        spinnerProductAdapter = new SpinnerProductAdapter(productModelList,this);
        binding.spinnerProduct.setAdapter(spinnerProductAdapter);

        binding.spinnerStores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stockModel = stockModelList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.spinnerProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                productModel = productModelList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        presenter.getStocks();
        presenter.getProducts();

        binding.btnAdd.setOnClickListener(v -> {
            String amount = binding.edtAmount.getText().toString();



            if (!amount.isEmpty()&&!amount.equals("0")&&stockModel!=null&&productModel!=null){
                StoreBalanceModel model = new StoreBalanceModel(stockModel.getId()+"",productModel.getId()+"",amount,stockModel.getAr_title(),productModel.getTitle());
                list.add(0,model);
                adapter.notifyItemInserted(0);
            }
        });
    }


    @Override
    public void onStocksSuccess(List<StockModel> list) {
        stockModelList.clear();
        stockModelList.addAll(list);
        spinnerStockAdapter.notifyDataSetChanged();
    }

    @Override
    public void onProductSuccess(List<SingleProductModel> list) {
        productModelList.clear();
        productModelList.addAll(list);
        spinnerProductAdapter.notifyDataSetChanged();

    }

    @Override
    public void onAddSuccess() {

    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}