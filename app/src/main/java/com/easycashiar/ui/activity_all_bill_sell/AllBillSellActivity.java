package com.easycashiar.ui.activity_all_bill_sell;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.easycashiar.R;
import com.easycashiar.adapters.BillSellAdapter;
import com.easycashiar.databinding.ActivityAllBillSellBinding;
import com.easycashiar.language.Language;
import com.easycashiar.models.AllBillOfSellModel;
import com.easycashiar.models.SingleBillOfSellModel;
import com.easycashiar.models.UserModel;
import com.easycashiar.mvp.activity_all_bill_sell_mvp.ActivityAllBillSellPresenter;
import com.easycashiar.mvp.activity_all_bill_sell_mvp.AllBillSellActivityView;
import com.easycashiar.preferences.Preferences;
import com.easycashiar.share.Common;
import com.easycashiar.ui.Activity_products_bill_sell.ProductsBillSellActivity;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class AllBillSellActivity extends AppCompatActivity implements AllBillSellActivityView {
    private ActivityAllBillSellBinding binding;
    private ActivityAllBillSellPresenter presenter;
    private String lang;
    private BillSellAdapter billSellAdapter;
    private List<SingleBillOfSellModel> singleBillOfSellModels;


    private UserModel userModel;
    private Preferences preferences;
    private ProgressDialog dialog;
    private String currency="";


    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_bill_sell);
        initView();
    }


    private void initView() {
        Paper.init(this);
        singleBillOfSellModels = new ArrayList<>();
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        lang = Paper.book().read("lang", "ar");

        binding.setLang(lang);
        binding.llBack.setOnClickListener(view -> {
            finish();
        });

        presenter = new ActivityAllBillSellPresenter(this, this);
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        billSellAdapter = new BillSellAdapter(this, singleBillOfSellModels, currency);
        binding.recView.setAdapter(billSellAdapter);
        presenter.getprofile(userModel);
        presenter.getbillSell(userModel);
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
    public void onProgressShow() {
        binding.progBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProgressHide() {
        binding.progBar.setVisibility(View.GONE);

    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccess(AllBillOfSellModel model) {
        singleBillOfSellModels.clear();
        singleBillOfSellModels.addAll(model.getData());
        billSellAdapter.notifyDataSetChanged();
        if(model.getData().size()==0){
            binding.llNoNotification.setVisibility(View.VISIBLE);
        }
        else {
            binding.llNoNotification.setVisibility(View.GONE);

        }

    }




    public void openbillsell(SingleBillOfSellModel singleBillOfSellModel) {
        Intent intent = new Intent(this, ProductsBillSellActivity.class);
        intent.putExtra("data", singleBillOfSellModel);
        startActivity(intent);
    }
    @Override
    public void onLoad() {
        if(dialog==null){
            dialog = Common.createProgressDialog(this, getString(R.string.wait));
            dialog.setCancelable(false);}
        else {
            dialog.dismiss();
        }
        dialog.show();
    }

    @Override
    public void onFinishload() {
        dialog.dismiss();
    }



    @Override
    public void onprofileload(UserModel body) {
        this.currency = body.getCurrency();
        billSellAdapter.currency=currency;
        billSellAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.getprofile(userModel);
    }
}