package com.easycashiar.ui.Activity_products_bill_sell;

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
import com.easycashiar.adapters.ProductsBillSellAdapter;
import com.easycashiar.databinding.ActivityProductsBillSellBinding;
import com.easycashiar.language.Language;
import com.easycashiar.models.CreateOrderModel;
import com.easycashiar.models.ItemCartModel;
import com.easycashiar.models.SingleBillOfSellModel;
import com.easycashiar.models.UserModel;
import com.easycashiar.mvp.activity_products_bill_Sell_mvp.ActivityProductsbillSellPresenter;
import com.easycashiar.mvp.activity_products_bill_Sell_mvp.ProductsBillSellActivityView;
import com.easycashiar.preferences.Preferences;
import com.easycashiar.share.Common;
import com.easycashiar.ui.activity_cart_bill_sell.CartBillSellActivity;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class ProductsBillSellActivity extends AppCompatActivity implements ProductsBillSellActivityView {
    private ActivityProductsBillSellBinding binding;
    private ActivityProductsbillSellPresenter presenter;
    private String lang;
    private ProductsBillSellAdapter productsAdapter;
    private List<SingleBillOfSellModel.SaleDetials> singleProductModelList;
    private Preferences preferences;
    private UserModel userModel;
    private CreateOrderModel createOrderModel;
    private List<ItemCartModel> itemCartModels;
    private SingleBillOfSellModel singleBillOfSellModel;
    private ProgressDialog dialog;
    private String currency;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_products_bill_sell);

        initView();
        getDataFromIntent();

    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            singleBillOfSellModel = (SingleBillOfSellModel) intent.getSerializableExtra("data");
            singleProductModelList.addAll(singleBillOfSellModel.getSale_details());
            productsAdapter.notifyDataSetChanged();

        }
    }

    private void initView() {

        singleProductModelList = new ArrayList<>();
        preferences = Preferences.getInstance();
        if (preferences.getCartDatabillsell(this) != null) {
            binding.setCartcount(preferences.getCartDatabillsell(this).getOrder_details().size());
        }
        userModel = preferences.getUserData(this);
        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);
        binding.llBack.setOnClickListener(view -> {
            finish();
        });
        presenter = new ActivityProductsbillSellPresenter(this, this);
        presenter.getprofile(userModel);
        productsAdapter = new ProductsBillSellAdapter(this, singleProductModelList,currency);
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setAdapter(productsAdapter);

        binding.frcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.Cart();
            }
        });

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
    public void onCart() {
        Intent intent = new Intent(this, CartBillSellActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    public void addproductstocart(SingleBillOfSellModel.SaleDetials singleProductModel) {

        if (preferences.getCartDatabillsell(this) != null) {

            int index = -1;
            createOrderModel = preferences.getCartDatabillsell(this);
            if(createOrderModel.getWarehouse_id().equals(singleBillOfSellModel.getWarehouse_id()+"")) {
                itemCartModels = preferences.getCartDatabillsell(this).getOrder_details();
                for (int i = 0; i < itemCartModels.size(); i++) {
                    if (singleProductModel.getProduct().getId() == itemCartModels.get(i).getProduct_id()) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    ItemCartModel itemCartModel = itemCartModels.get(index);
                    if (itemCartModel.getStock() >= ((1 * itemCartModel.getAmount2()) + itemCartModel.getAmount())) {

                        itemCartModel.setAmount((1 * itemCartModel.getAmount2()) + itemCartModel.getAmount());
                        itemCartModels.set(index, itemCartModel);
                        createOrderModel.setOrder_details(itemCartModels);
                    }
                } else {
                    ItemCartModel itemCartModel = new ItemCartModel();
                    itemCartModel.setAmount(1);
                    itemCartModel.setAmount2(1);

                    itemCartModel.setImage(singleProductModel.getProduct().getImage());
                    itemCartModel.setPrice_value(singleProductModel.getProduct().getProduct_price());
                    itemCartModel.setProduct_id(singleProductModel.getProduct().getId());
                    itemCartModel.setTitle(singleProductModel.getProduct().getTitle());
                    itemCartModel.setType(singleProductModel.getProduct().getProduct_type());
                    itemCartModel.setStock(singleProductModel.getAmount());
                    itemCartModels.add(itemCartModel);
                    createOrderModel.setOrder_details(itemCartModels);
                }
            }
            else {
                Toast.makeText(this, getResources().getString(R.string.dont_add), Toast.LENGTH_LONG).show();

            }
        }
        else {
            itemCartModels = new ArrayList<>();
            createOrderModel = new CreateOrderModel();
            if(createOrderModel!=null&&singleBillOfSellModel!=null){
                createOrderModel.setWarehouse_id(singleBillOfSellModel.getWarehouse_id()+"");
            }
            createOrderModel.setClient_id(singleBillOfSellModel.getClient_id());
            ItemCartModel itemCartModel = new ItemCartModel();
            itemCartModel.setAmount(1);
            itemCartModel.setAmount2(1);

            itemCartModel.setImage(singleProductModel.getProduct().getImage());
            itemCartModel.setPrice_value(singleProductModel.getProduct().getProduct_price());
            itemCartModel.setProduct_id(singleProductModel.getProduct().getId());
            itemCartModel.setTitle(singleProductModel.getProduct().getTitle());
            itemCartModel.setType(singleProductModel.getProduct().getProduct_type());
            itemCartModel.setStock(singleProductModel.getAmount());
            itemCartModels.add(itemCartModel);
            createOrderModel.setOrder_details(itemCartModels);
        }
        preferences.create_update_cartbillsell(this, createOrderModel);
        binding.setCartcount(createOrderModel.getOrder_details().size());
        Toast.makeText(this,getResources().getString(R.string.addtocartsucess),Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if (preferences != null && preferences.getCartDatabillsell(this) == null) {
            binding.setCartcount(0);
        }
        else {
            binding.setCartcount(preferences.getCartDatabillsell(this).getOrder_details().size());
        }
    }
    @Override
    public void onLoad() {
        if (dialog == null) {
            dialog = Common.createProgressDialog(this, getString(R.string.wait));
            dialog.setCancelable(false);
        } else {
            dialog.dismiss();
        }
        dialog.show();
    }

    @Override
    public void onFinishload() {
        dialog.dismiss();
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }


    @Override
    public void onprofileload(UserModel body) {
        this.currency = body.getCurrency();
        productsAdapter.currency=currency;
        productsAdapter.notifyDataSetChanged();
    }

}