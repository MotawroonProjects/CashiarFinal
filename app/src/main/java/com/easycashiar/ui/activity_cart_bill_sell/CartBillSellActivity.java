package com.easycashiar.ui.activity_cart_bill_sell;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.easycashiar.R;
import com.easycashiar.adapters.CartSellBuyAdapter;
import com.easycashiar.adapters.Swipe;
import com.easycashiar.databinding.ActivityCartBillSellBinding;
import com.easycashiar.databinding.DialogInpiutBinding;
import com.easycashiar.language.Language;
import com.easycashiar.models.CreateOrderModel;
import com.easycashiar.models.ItemCartModel;
import com.easycashiar.models.UserModel;
import com.easycashiar.mvp.activity_cart_bill_sell_mvp.ActivityCartBillSellPresenter;
import com.easycashiar.mvp.activity_cart_bill_sell_mvp.CartBillSellActivityView;
import com.easycashiar.preferences.Preferences;
import com.easycashiar.share.Common;
import com.easycashiar.ui.activity_payment_bill_Sell.PaymentBillSellActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class CartBillSellActivity extends AppCompatActivity implements CartBillSellActivityView, Swipe.SwipeListener {
    private ActivityCartBillSellBinding binding;
    private ActivityCartBillSellPresenter presenter;
    private String lang;
    private CartSellBuyAdapter cartSellBuyAdapter;
    private List<ItemCartModel> itemCartModelList;
    private Preferences preferences;

    private CreateOrderModel createOrderModel;
    private double total;

    private UserModel userModel;
    private String type;
    private ProgressDialog dialog;
    private String currency = "";

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart_bill_sell);
        initView();
    }


    private void initView() {
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        itemCartModelList = new ArrayList<>();
        getdata();
        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);
        binding.llBack.setOnClickListener(view -> {
            finish();
        });
        presenter = new ActivityCartBillSellPresenter(this, this);
        presenter.getprofile(userModel);
        cartSellBuyAdapter = new CartSellBuyAdapter(itemCartModelList, this, currency);
       presenter.getprofile(userModel);
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setAdapter(cartSellBuyAdapter);
        ItemTouchHelper.SimpleCallback simpleCallback = new Swipe(this, 0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        ItemTouchHelper helper = new ItemTouchHelper(simpleCallback);
        helper.attachToRecyclerView(binding.recView);
        binding.btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.checkdata();
            }
        });
    //    Log.e("dldkkd",createOrderModel.getWarehouse_id());
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
    public void onopenpay() {


        preferences.create_update_cartbillsell(this, createOrderModel);
        Intent intent = new Intent(this, PaymentBillSellActivity.class);
        intent.putExtra("total", total);
        startActivityForResult(intent, 1);

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
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onprofileload(UserModel body) {
        this.currency = body.getCurrency();
        cartSellBuyAdapter.currency = currency;
        cartSellBuyAdapter.notifyDataSetChanged();
    }


    @Override
    public void onSwipe(int pos, int dir) {
        deleteItem(pos);
    }


    public void increase_decrease(ItemCartModel model, int adapterPosition) {
        itemCartModelList.set(adapterPosition, model);
        cartSellBuyAdapter.notifyItemChanged(adapterPosition);
        createOrderModel.setOrder_details(itemCartModelList);
        preferences.create_update_cartbillsell(this, createOrderModel);
        calculateTotal();
    }

    private void calculateTotal() {
        total = 0;
        for (ItemCartModel model : itemCartModelList) {

            total += model.getAmount() * model.getPrice_value();

        }

        binding.tvTotal.setText(String.format(Locale.ENGLISH, "%s %s", String.valueOf(total), getString(R.string.sar)));
    }

    public void deleteItem(int adapterPosition) {
        itemCartModelList.remove(adapterPosition);
        cartSellBuyAdapter.notifyItemRemoved(adapterPosition);
        createOrderModel.setOrder_details(itemCartModelList);
        preferences.create_update_cartbillsell(this, createOrderModel);
        calculateTotal();
        if (itemCartModelList.size() == 0) {
            binding.llEmptyCart.setVisibility(View.VISIBLE);
            binding.consTotal.setVisibility(View.GONE);
            preferences.clearCartbillsell(this);
        }
    }

    public void CreateDialogAlert(Context context, int pos) {
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .create();

        DialogInpiutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_inpiut, null, false);


        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     String amount = binding.edtwieght.getText().toString();
                                                     if (!amount.isEmpty()) {
                                                         itemCartModelList.get(pos).setAmount2(Double.parseDouble(amount));
                                                         itemCartModelList.get(pos).setAmount(Double.parseDouble(amount) * itemCartModelList.get(pos).getAmount());
                                                         createOrderModel.setOrder_details(itemCartModelList);
                                                         preferences.create_update_cartbillsell(CartBillSellActivity.this, createOrderModel);
                                                         cartSellBuyAdapter.notifyDataSetChanged();

                                                         dialog.dismiss();
                                                     } else {
                                                         binding.edtwieght.setError(context.getResources().getString(R.string.field_required));
                                                     }
                                                 }
                                             }

        );
        dialog.getWindow().getAttributes().windowAnimations = R.style.Theme_App;
        dialog.setCanceledOnTouchOutside(false);
        dialog.setView(binding.getRoot());
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            getdata();
        }
    }

    private void getdata() {
        if (itemCartModelList != null) {
            itemCartModelList.clear();
        }
        if (preferences.getCartDatabillsell(this) != null) {
            createOrderModel = preferences.getCartDatabillsell(this);
            itemCartModelList.addAll(createOrderModel.getOrder_details());
            calculateTotal();
        }
        if (itemCartModelList == null || itemCartModelList.size() == 0) {
            binding.llEmptyCart.setVisibility(View.VISIBLE);
            binding.consTotal.setVisibility(View.GONE);
            preferences.clearCartbillsell(this);
        }
        if (cartSellBuyAdapter != null) {
            cartSellBuyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (preferences != null) {
            getdata();
            presenter.getprofile(userModel);
        }
    }

}