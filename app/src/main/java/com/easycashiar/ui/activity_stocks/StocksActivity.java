package com.easycashiar.ui.activity_stocks;

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

import com.easycashiar.R;
import com.easycashiar.adapters.StocksAdapter;
import com.easycashiar.adapters.StocksSwipe;
import com.easycashiar.databinding.ActivityCategoriesBinding;
import com.easycashiar.databinding.ActivityStocksBinding;
import com.easycashiar.language.Language;
import com.easycashiar.models.StockModel;
import com.easycashiar.mvp.activity_stocks_mvp.ActivityStocksPresenter;
import com.easycashiar.mvp.activity_stocks_mvp.StocksActivityView;
import com.easycashiar.ui.activity_add_stock.AddStockActivity;
import com.easycashiar.ui.activity_warehouse_product.ProductsWareHouseActivity;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class StocksActivity extends AppCompatActivity implements StocksActivityView, StocksSwipe.SwipeListener {
    private ActivityStocksBinding binding;
    private ActivityStocksPresenter presenter;
    private String lang;
    private List<StockModel> list;
    private StocksAdapter adapter;

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
        list = new ArrayList<>();
        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);
        binding.llBack.setOnClickListener(view -> {
            finish();
        });
        presenter = new ActivityStocksPresenter(this, this);

        adapter = new StocksAdapter(this, list);
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setAdapter(adapter);
        ItemTouchHelper.SimpleCallback simpleCallback = new StocksSwipe(this, 0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        ItemTouchHelper helper = new ItemTouchHelper(simpleCallback);
        helper.attachToRecyclerView(binding.recView);


        binding.fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddStockActivity.class);
            startActivityForResult(intent, 1);

        });
        presenter.getData();

    }

    @Override
    public void onSuccess(List<StockModel> list) {
        if (list.size() > 0) {
            this.list.clear();
            this.list.addAll(list);
            binding.llNoData.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        } else {
            binding.llNoData.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onDeleteSuccess(int pos) {
        list.remove(pos);
        adapter.notifyItemRemoved(pos);
        if (list.size()==0){
            binding.llNoData.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onDeleteFailed(String msg, int pos) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        adapter.notifyItemRemoved(pos);

    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProgressShow() {
        binding.progBar.setVisibility(View.VISIBLE);
        binding.llNoData.setVisibility(View.GONE);
        list.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onProgressHide() {
        binding.progBar.setVisibility(View.GONE);

    }


    @Override
    public void onSwipe(int pos, int dir) {
        StockModel model = list.get(pos);
        presenter.delete(model.getId() + "", pos);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            presenter.getData();
        }
    }

    public void editStock(StockModel stockModel) {
        Intent intent = new Intent(this,AddStockActivity.class);
        intent.putExtra("data",stockModel);
        startActivityForResult(intent,1);
    }

    public void showProducts(StockModel stockModel) {
        Intent intent = new Intent(this, ProductsWareHouseActivity.class);
        intent.putExtra("data",stockModel);
        startActivity(intent);
    }
}