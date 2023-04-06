package com.easycashiar.ui.activity_stock_balance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.easycashiar.R;
import com.easycashiar.adapters.SpinnerProductAdapter;
import com.easycashiar.adapters.SpinnerStockAdapter;
import com.easycashiar.adapters.StoreBalanceAdapter;
import com.easycashiar.databinding.ActivityStockBalanceBinding;
import com.easycashiar.databinding.ActivityStocksBinding;
import com.easycashiar.databinding.CustomAlertUpdateDeleteBinding;
import com.easycashiar.language.Language;
import com.easycashiar.models.SingleProductModel;
import com.easycashiar.models.StockModel;
import com.easycashiar.models.StoreBalanceDataModel;
import com.easycashiar.models.StoreBalanceModel;
import com.easycashiar.mvp.stock_balance_activity_mvp.ActivityStockBalancePresenter;
import com.easycashiar.mvp.stock_balance_activity_mvp.StocksBalanceActivityView;

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
    private ActivityStockBalancePresenter presenter;
    private List<SingleProductModel> productModelList;
    private SpinnerProductAdapter spinnerProductAdapter;
    private StockModel stockModel;
    private SingleProductModel productModel;
    private boolean isUpdate = false;
    private int selectedPos = -1;

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

        presenter = new ActivityStockBalancePresenter(this, this);

        binding.llBack.setOnClickListener(view -> {
            finish();
        });

        adapter = new StoreBalanceAdapter(this, list);
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setAdapter(adapter);

        spinnerStockAdapter = new SpinnerStockAdapter(stockModelList, this);
        binding.spinnerStores.setAdapter(spinnerStockAdapter);

        spinnerProductAdapter = new SpinnerProductAdapter(productModelList, this);
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
            if (!amount.isEmpty() && !amount.equals("0") && stockModel != null && productModel != null) {

                StoreBalanceModel model = new StoreBalanceModel(stockModel.getId() + "", productModel.getId() + "", amount, stockModel.getAr_title(), productModel.getTitle());
                if (!isUpdate) {
                    list.add(0, model);

                } else {
                    list.set(selectedPos, model);
                }
                adapter.notifyDataSetChanged();
                binding.edtAmount.setText("");
                selectedPos = -1;
                isUpdate = false;


            }

            binding.btnUpload.setVisibility(View.VISIBLE);
            binding.btnAdd.setText(getString(R.string.add));
        });
        binding.btnUpload.setOnClickListener(v -> {
            if (list.size()>0){
                presenter.addData(new StoreBalanceDataModel(list));

            }
        });
    }

    private int getStorePos(String id) {
        int pos = 0;
        for (int index = 0; index < stockModelList.size(); index++) {
            StockModel model = stockModelList.get(index);
            if (String.valueOf(model.getId()).equals(id)) {
                pos = index;
                return pos;
            }
        }

        return pos;
    }

    private int getProductPos(String id) {
        int pos = 0;
        for (int index = 0; index < productModelList.size(); index++) {
            SingleProductModel model = productModelList.get(index);
            if (String.valueOf(model.getId()).equals(id)) {
                pos = index;
                return pos;
            }
        }

        return pos;
    }

    public void showChooseDialog(int pos, StoreBalanceModel model) {


        AlertDialog builder = new AlertDialog.Builder(this)
                .create();
        CustomAlertUpdateDeleteBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.custom_alert_update_delete, null, false);
        binding.btnDelete.setOnClickListener(v -> {
            list.remove(pos);
            adapter.notifyItemRemoved(pos);
            if (list.size() == 0) {
                StockBalanceActivity.this.binding.btnUpload.setVisibility(View.GONE);

            }
            builder.dismiss();
        });
        binding.btnUpdate.setOnClickListener(v -> {
            isUpdate = true;
            selectedPos = pos;
            StockBalanceActivity.this.binding.btnAdd.setText(getString(R.string.update));
            StockBalanceActivity.this.binding.edtAmount.setText(model.getAmount());
            StockBalanceActivity.this.binding.spinnerStores.setSelection(getStorePos(model.getWarehouse_id()));
            StockBalanceActivity.this.binding.spinnerProduct.setSelection(getProductPos(model.getProduct_id()));
            builder.dismiss();
        });
        binding.tvDismiss.setOnClickListener(v -> {

            builder.dismiss();
        });
        builder.setView(binding.getRoot());
        builder.setCancelable(true);
        builder.setCanceledOnTouchOutside(false);
        builder.show();
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
        list.clear();
        adapter.notifyDataSetChanged();
        Toast.makeText(this,getString(R.string.suc), Toast.LENGTH_SHORT).show();
        binding.btnUpload.setVisibility(View.GONE);
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}