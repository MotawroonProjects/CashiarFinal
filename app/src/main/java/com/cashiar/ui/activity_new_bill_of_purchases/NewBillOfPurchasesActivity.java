package com.cashiar.ui.activity_new_bill_of_purchases;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cashiar.R;
import com.cashiar.adapters.DelteNewproductSwipe;
import com.cashiar.adapters.NewProductBillSellAdapter;
import com.cashiar.adapters.ProductAutoAdapter;
import com.cashiar.adapters.SpinnerCategoryAdapter;
import com.cashiar.adapters.SpinnerCustomerAdapter;
import com.cashiar.adapters.SpinnerStockAdapter;
import com.cashiar.databinding.ActivityANewBillOfPurchasesBinding;
import com.cashiar.databinding.DialogInpiutBinding;
import com.cashiar.databinding.DialogNewBillPurchaasesBinding;
import com.cashiar.language.Language;
import com.cashiar.models.AllCategoryModel;
import com.cashiar.models.AllCustomersModel;
import com.cashiar.models.AllProductsModel;
import com.cashiar.models.CreateBuyOrderModel;
import com.cashiar.models.ItemCartModel;
import com.cashiar.models.PaymentModel;
import com.cashiar.models.SingleCategoryModel;
import com.cashiar.models.SingleCustomerSuplliersModel;
import com.cashiar.models.SingleProductModel;
import com.cashiar.models.StockDataModel;
import com.cashiar.models.StockModel;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.activity_new_bill_of_purchases_mvp.ActivityNewBillOfPurchasesPresenter;
import com.cashiar.mvp.activity_new_bill_of_purchases_mvp.NewBillOfPurchasesActivityView;
import com.cashiar.preferences.Preferences;
import com.cashiar.share.Common;
import com.cashiar.ui.activity_add_subliers.AddSubliersActivity;
import com.cashiar.ui.scanner.ActivityScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class NewBillOfPurchasesActivity extends AppCompatActivity implements NewBillOfPurchasesActivityView, DelteNewproductSwipe.SwipeListener {
    private ActivityANewBillOfPurchasesBinding binding;
    private ActivityNewBillOfPurchasesPresenter presenter;
    private String lang;
    private ProductAutoAdapter productsAdapter;

    private List<SingleProductModel> singleProductModels;
    private List<ItemCartModel> singleProductModelList;
    private NewProductBillSellAdapter newProductBillSellAdapter;
    private float dX = 0, dY = 0;
    private float downRawX, downRawY;
    private UserModel userModel;
    private Preferences preferences;
    private ProgressDialog dialog;
    private String cat = "all", query = "", stock;
    private int pos;
    private String currency = "";
    private int taderid;
    private UserModel body;
    private int index;
    private double total;

    private List<SingleCustomerSuplliersModel> singleCustomerSuplliersModels;
    private SpinnerCustomerAdapter spinnercustomerAdapter;
    private int client_id;
    private CreateBuyOrderModel createOrderModel;
    private String name;
    private PaymentModel paymentModel;
    private double paid;
    private List<StockModel> stockModelList;
    private SpinnerStockAdapter spinnerStockAdapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_a_new_bill_of_purchases);
        initView();
    }


    private void initView() {
        stockModelList = new ArrayList<>();
        Paper.init(this);
        paymentModel = new PaymentModel();
        createOrderModel = new CreateBuyOrderModel();
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        singleCustomerSuplliersModels = new ArrayList<>();
        lang = Paper.book().read("lang", "ar");
        singleProductModels = new ArrayList<>();
        singleProductModelList = new ArrayList<>();
        binding.setLang(lang);
        binding.llBack.setOnClickListener(view -> {
            finish();
        });

        presenter = new ActivityNewBillOfPurchasesPresenter(this, this);
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setHasFixedSize(true);
        productsAdapter = new ProductAutoAdapter(this, R.layout.product_auto_row, singleProductModels);
        newProductBillSellAdapter = new NewProductBillSellAdapter(this, singleProductModelList, currency);
        binding.recView.setAdapter(newProductBillSellAdapter);
        presenter.getprofile(userModel);
        presenter.getsuppliers(userModel);
        presenter.getStocks(userModel);
        binding.edtSearch.setAdapter(productsAdapter);

        ItemTouchHelper.SimpleCallback simpleCallback = new DelteNewproductSwipe(this, 0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        ItemTouchHelper helper = new ItemTouchHelper(simpleCallback);
        helper.attachToRecyclerView(binding.recView);
        binding.spStock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                stock = stockModelList.get(i).getId() + "";


                presenter.getproducts(userModel, stock, query);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // presenter.getproducts(userModel, cat, query);
//        binding.llMap.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                long duration = motionEvent.getEventTime() - motionEvent.getDownTime();
//
//
//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
//
//                int action = motionEvent.getAction();
//
//                if (action == MotionEvent.ACTION_DOWN) {
//
//                    downRawX = motionEvent.getRawX();
//                    downRawY = motionEvent.getRawY();
//                    dX = view.getX() - downRawX;
//                    dY = view.getY() - downRawY;
//
//                    return true; // Consumed
//
//                } else if (action == MotionEvent.ACTION_MOVE) {
//
//                    int viewWidth = view.getWidth();
//                    int viewHeight = view.getHeight();
//
//                    View viewParent = (View) view.getParent();
//                    int parentWidth = viewParent.getWidth();
//                    int parentHeight = viewParent.getHeight();
//
//                    float newX = motionEvent.getRawX() + dX;
//                    newX = Math.max(layoutParams.leftMargin, newX);
//                    newX = Math.min(parentWidth - viewWidth - layoutParams.rightMargin, newX); // Don't allow the FAB past the right hand side of the parent
//
//                    float newY = motionEvent.getRawY() + dY;
//                    newY = Math.max(layoutParams.topMargin, newY); // Don't allow the FAB past the top of the parent
//                    newY = Math.min(parentHeight - viewHeight - layoutParams.bottomMargin, newY); // Don't allow the FAB past the bottom of the parent
//
//                    view.animate()
//                            .x(newX)
//                            .y(newY)
//                            .setDuration(0)
//                            .start();
//
//                    return true; // Consumed
//
//                } else if (action == MotionEvent.ACTION_UP) {
//
//                    float upRawX = motionEvent.getRawX();
//                    float upRawY = motionEvent.getRawY();
//
//                    float upDX = upRawX - downRawX;
//                    float upDY = upRawY - downRawY;
//
//                    // A drag
//
//                    if (duration < 100) {
//
//                        if (body!=null&&body.getCurrency() != null && body.getTax_amount() != null) {
//                            presenter.addproducts();
//                        } else {
//                            Common.CreateDialogAlertProfile(ProductsActivity.this, getResources().getString(R.string.please_complete_profile_first));
//
//                        }
//                    }
//                    return false; // Consumed
//
//
//                } else {
//                    if (duration < 100) {
//                        if (body!=null&&body.getCurrency() != null && body.getTax_amount() != null) {
//                            presenter.addproducts();
//                        } else {
//                            Common.CreateDialogAlertProfile(ProductsActivity.this, getResources().getString(R.string.please_complete_profile_first));
//
//                        }
//                    }
//                    //return super.onTouchEvent(motionEvent);
//
//                }
//
//                return false;
//            }
//
//
//        });

        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                query = binding.edtSearch.getText().toString();
                if (query.isEmpty()) {
                    query = "";
                }
                presenter.getproducts(userModel, stock, query);

            }
        });
        binding.frbarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewBillOfPurchasesActivity.this, ActivityScanner.class);
                startActivityForResult(intent, 100);
            }
        });
        binding.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singleProductModelList.clear();
                newProductBillSellAdapter.notifyDataSetChanged();
                binding.ll.setVisibility(View.GONE);
                calculateTotal();

            }
        });
        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDialogConfirmAlert(NewBillOfPurchasesActivity.this);
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
    public void onSuccessDelete() {
        singleProductModels.remove(pos);
    }

    @Override
    public void onprofileload(UserModel body) {
        this.currency = body.getCurrency();
        newProductBillSellAdapter.currency = currency;
        newProductBillSellAdapter.notifyDataSetChanged();
        // Log.e("dldkdk",taxamount);
        this.taderid = body.getTrader_id();
        this.body = body;
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
    public void onproductSuccess(AllProductsModel allProductsModel) {

Log.e("sizess",allProductsModel.getData().size()+"");
        singleProductModels.clear();
        singleProductModels.addAll(allProductsModel.getData());


        productsAdapter.notifyDataSetChanged();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            cat = "all";
//            query = "";
//            presenter.getproducts(userModel, cat, query);
//        }
//    }

    @Override
    public void onSwipe(int pos, int dir) {
        this.pos = pos;
        singleProductModelList.remove(pos);
        newProductBillSellAdapter.notifyDataSetChanged();
        if (singleProductModelList.size() == 0) {
            binding.ll.setVisibility(View.GONE);

        }
        calculateTotal();

        //  presenter.delteproduct(singleProductModels.get(pos).getId(), userModel);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.getprofile(userModel);
    }

    public void additem(SingleProductModel singleProductModel) {
        binding.ll.setVisibility(View.VISIBLE);

        ItemCartModel itemCartModel = new ItemCartModel();

        itemCartModel.setProduct_id(singleProductModel.getId());
        itemCartModel.setStockid(Integer.parseInt(stock));

        binding.edtSearch.setText(singleProductModel.getTitle());
        if (singleProductModelList.size() > 0 && singleProductModelList.get(0).getStockid() != itemCartModel.getStockid()) {
            Toast.makeText(this, getResources().getString(R.string.dont_add), Toast.LENGTH_LONG).show();
        } else {
            if (iscontain(itemCartModel)) {


                Log.e("dlldkkdkd", "dklkdkdkkd");
                ItemCartModel itemCartModel1 = singleProductModelList.get(index);
                itemCartModel1.setAmount((itemCartModel1.getAmount2()) + itemCartModel1.getAmount());
                singleProductModelList.set(index, itemCartModel1);
            } else {
                itemCartModel.setAmount(1);
                itemCartModel.setAmount2(1);
                itemCartModel.setImage(singleProductModel.getImage());
                itemCartModel.setPrice_value(singleProductModel.getProduct_cost());
                itemCartModel.setProduct_id(singleProductModel.getId());
                itemCartModel.setTitle(singleProductModel.getTitle());
                itemCartModel.setType(singleProductModel.getProduct_type());
                itemCartModel.setStock(singleProductModel.getStock_amount());

                singleProductModelList.add(itemCartModel);

            }
            newProductBillSellAdapter.notifyDataSetChanged();
            calculateTotal();
        }
    }

    private boolean iscontain(ItemCartModel singleProductModel) {
        for (int i = 0; i < singleProductModelList.size(); i++) {
            if (singleProductModel.getProduct_id() == singleProductModelList.get(i).getProduct_id()) {
                index = i;
                return true;
            }

        }
        return false;
    }

    public void increase(int layoutPosition) {
        ItemCartModel itemCartModel1 = singleProductModelList.get(layoutPosition);
        itemCartModel1.setAmount((itemCartModel1.getAmount2()) + itemCartModel1.getAmount());
        singleProductModelList.set(layoutPosition, itemCartModel1);
        newProductBillSellAdapter.notifyDataSetChanged();
    }

    public void CreateDialogAlert(Context context, int pos, String type) {
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .create();

        DialogInpiutBinding dialogInpiutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_inpiut, null, false);
        if (!type.equals("weight")) {
            dialogInpiutBinding.edtwieght.setHint(getResources().getString(R.string.amount));
        }

        dialogInpiutBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
                                                             @Override
                                                             public void onClick(View v) {
                                                                 String amount = dialogInpiutBinding.edtwieght.getText().toString();
                                                                 if (!amount.isEmpty()) {
                                                                     dialog.dismiss();
                                                                     singleProductModelList.get(pos).setAmount2(Double.parseDouble(amount));
                                                                     singleProductModelList.get(pos).setAmount(Double.parseDouble(amount) + singleProductModelList.get(pos).getAmount());
                                                                     newProductBillSellAdapter.notifyItemChanged(pos);
                                                                     calculateTotal();
                                                                 } else {
                                                                     dialogInpiutBinding.edtwieght.setError(context.getResources().getString(R.string.field_required));
                                                                 }
                                                             }
                                                         }

        );
        dialog.getWindow().getAttributes().windowAnimations = R.style.Theme_App;
        dialog.setCanceledOnTouchOutside(false);
        dialog.setView(dialogInpiutBinding.getRoot());
        dialog.show();
    }

    public void CreateDialogConfirmAlert(Context context) {
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .create();

        DialogNewBillPurchaasesBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_new_bill_purchaases, null, false);

        binding.spcat.setAdapter(spinnercustomerAdapter);
        binding.setDate(System.currentTimeMillis());
        binding.tvTotal.setText((total) + "");
        binding.tvstay.setText((total - paid) + "");
        binding.spcat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    client_id = 0;
                    paymentModel.setId("");

                    binding.setModel(paymentModel);
                } else {
                    client_id = singleCustomerSuplliersModels.get(i).getId();
                    name = singleCustomerSuplliersModels.get(i).getName();
                    paymentModel.setId(client_id + "");

                    binding.setModel(paymentModel);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.btnaddcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addSupplier();
            }
        });
        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createOrderModel.setSupplier_id(client_id);
                createOrderModel.setWarehouse_id(singleProductModelList.get(0).getStockid()+"");
                String date[] = binding.tvdate.getText().toString().split("/");
                createOrderModel.setDate(date[2] + "-" + date[1] + "-" + date[0]);
                createOrderModel.setTotal_price(Math.round(total));
                createOrderModel.setPaid_price(Math.round(paid));
                createOrderModel.setRemaining_price(Math.round(total - paid));
                createOrderModel.setOrder_details(singleProductModelList);
                presenter.checkData(paymentModel, createOrderModel, userModel);
            }
        });
        binding.edtpaid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.edtpaid.setError(null);

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.edtpaid.setError(null);

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    paid = Double.parseDouble(editable.toString());
                    if (paid <= total) {
//                        Log.e("ldkkd",(total + Double.parseDouble(taxamount) - discount)+" "+paid);

                        binding.tvstay.setText((total - paid) + "");
                    } else {
                        binding.edtpaid.setError(getResources().getString(R.string.paid_must_small_or));
                    }
                } catch (Exception e) {
                    binding.tvstay.setText((total - paid) + "");
                }
            }
        });

        dialog.getWindow().getAttributes().windowAnimations = R.style.Theme_App;
        dialog.setCanceledOnTouchOutside(false);
        dialog.setView(binding.getRoot());
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {
            presenter.getsingleproduct(userModel, data.getStringExtra("code"));

        } else if (requestCode == 1) {
            presenter.getsuppliers(userModel);
        }

    }

    @Override
    public void onproductSuccess(SingleProductModel allProductsModel) {
        additem(allProductsModel);
    }

    private void calculateTotal() {
        total = 0;
        for (ItemCartModel model : singleProductModelList) {

            total += model.getAmount() * model.getPrice_value();

        }

        binding.tvTotal.setText(String.format(Locale.ENGLISH, "%s %s", String.valueOf(total), getString(R.string.sar)));
    }


    @Override
    public void ondSupplierSuccess(AllCustomersModel model) {
        singleCustomerSuplliersModels.clear();
        if (lang.equals("en")) {

            singleCustomerSuplliersModels.add(new SingleCustomerSuplliersModel("Choose Supplier"));
        } else {

            singleCustomerSuplliersModels.add(new SingleCustomerSuplliersModel("اختر مورد"));
        }
        //Log.e("dlldldl",model.getData().size()+"");
        singleCustomerSuplliersModels.addAll(model.getData());
        if (spinnercustomerAdapter == null) {
            spinnercustomerAdapter = new SpinnerCustomerAdapter(singleCustomerSuplliersModels, this);
        } else {
            spinnercustomerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSupplier() {
        Intent intent = new Intent(this, AddSubliersActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onsucess() {
        Toast.makeText(this, getResources().getString(R.string.suc), Toast.LENGTH_LONG).show();
        finish();

    }

    @Override
    public void onSuccess(StockDataModel model) {

        Log.e("dlldldl", model.getData().size() + "");
        stockModelList.addAll(model.getData());
        spinnerStockAdapter = new SpinnerStockAdapter(stockModelList, this);
        binding.spStock.setAdapter(spinnerStockAdapter);

    }

}