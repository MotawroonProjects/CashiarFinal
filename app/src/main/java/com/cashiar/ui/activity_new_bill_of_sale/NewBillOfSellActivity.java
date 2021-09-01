package com.cashiar.ui.activity_new_bill_of_sale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.cashiar.adapters.DelteNewproductSwipe;
import com.cashiar.adapters.DelteproductSwipe;
import com.cashiar.adapters.NewProductBillSellAdapter;
import com.cashiar.adapters.ProductAutoAdapter;
import com.cashiar.adapters.ProductsAdapter;
import com.cashiar.adapters.SpinnerCategoryAdapter;
import com.cashiar.adapters.SpinnerCustomerAdapter;
import com.cashiar.adapters.SpinnerDiscountAdapter;
import com.cashiar.databinding.ActivityANewBillOfSaleBinding;
import com.cashiar.databinding.ActivityProductsBinding;
import com.cashiar.databinding.DialogInpiutBinding;
import com.cashiar.databinding.DialogNewBillSellBinding;
import com.cashiar.language.Language;
import com.cashiar.models.AllCategoryModel;
import com.cashiar.models.AllCustomersModel;
import com.cashiar.models.AllDiscountsModel;
import com.cashiar.models.AllProductsModel;
import com.cashiar.models.BillModel;
import com.cashiar.models.CreateOrderModel;
import com.cashiar.models.ItemCartModel;
import com.cashiar.models.PaymentModel;
import com.cashiar.models.SingleCategoryModel;
import com.cashiar.models.SingleCustomerSuplliersModel;
import com.cashiar.models.SingleDiscountModel;
import com.cashiar.models.SingleProductModel;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.activity_new_bill_of_sell_mvp.ActivityNewSellOfSellPresenter;
import com.cashiar.mvp.activity_new_bill_of_sell_mvp.NewSellOfSellActivityView;
import com.cashiar.mvp.activity_products_mvp.ActivityProductsPresenter;
import com.cashiar.mvp.activity_products_mvp.ProductsActivityView;
import com.cashiar.preferences.Preferences;
import com.cashiar.share.Common;
import com.cashiar.ui.activity_add_Customer.AddCustomerActivity;
import com.cashiar.ui.activity_add_product.AddProductActivity;
import com.cashiar.ui.activity_bill_Sell.BillSellActivity;
import com.cashiar.ui.activity_cart_bill_sell.CartBillSellActivity;
import com.cashiar.ui.activity_products_sell.ProductsSellActivity;
import com.cashiar.ui.scanner.ActivityScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class NewBillOfSellActivity extends AppCompatActivity implements NewSellOfSellActivityView, DelteNewproductSwipe.SwipeListener {
    private ActivityANewBillOfSaleBinding binding;
    private ActivityNewSellOfSellPresenter presenter;
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
    private String cat = "all", query = "";
    private int pos;
    private String currency = "";
    private int taderid;
    private UserModel body;
    private int index;
    private double total;
    private SpinnerDiscountAdapter spinnerDiscountAdapter;
    private List<SingleDiscountModel> singleDiscountModels;
    private int coupon_id;
    private double discount;
    private String taxamount;
    private List<SingleCustomerSuplliersModel> singleCustomerSuplliersModels;
    private SpinnerCustomerAdapter spinnercustomerAdapter;
    private int client_id;
    private CreateOrderModel createOrderModel;
    private String name;
    private PaymentModel paymentModel;
    private double paid;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_a_new_bill_of_sale);
        initView();
    }


    private void initView() {
        Paper.init(this);
        paymentModel = new PaymentModel();
        createOrderModel = new CreateOrderModel();
        singleDiscountModels = new ArrayList<>();
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

        presenter = new ActivityNewSellOfSellPresenter(this, this);
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setHasFixedSize(true);        productsAdapter = new ProductAutoAdapter(this, R.layout.product_auto_row, singleProductModels);
        newProductBillSellAdapter = new NewProductBillSellAdapter(this, singleProductModelList, currency);
        binding.recView.setAdapter(newProductBillSellAdapter);
        presenter.getprofile(userModel);
        presenter.getdiscount(userModel);
        presenter.getcustomer(userModel);
        binding.edtSearch.setAdapter(productsAdapter);
        ItemTouchHelper.SimpleCallback simpleCallback = new DelteNewproductSwipe(this, 0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        ItemTouchHelper helper = new ItemTouchHelper(simpleCallback);
        helper.attachToRecyclerView(binding.recView);
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
                if (query == null || query.isEmpty()) {
                    query = "all";
                }
                presenter.getproducts(userModel, cat, query);

            }
        });
        binding.frbarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewBillOfSellActivity.this, ActivityScanner.class);
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
                CreateDialogConfirmAlert(NewBillOfSellActivity.this);
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
        this.taxamount = body.getTax_amount();
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


        binding.edtSearch.setText(singleProductModel.getTitle());
        if (iscontain(itemCartModel)) {


            Log.e("dlldkkdkd", "dklkdkdkkd");
            ItemCartModel itemCartModel1 = singleProductModelList.get(index);
            itemCartModel1.setAmount((itemCartModel1.getAmount2()) + itemCartModel1.getAmount());
            singleProductModelList.set(index, itemCartModel1);
        } else {
            itemCartModel.setAmount(1);
            itemCartModel.setAmount2(1);
            itemCartModel.setImage(singleProductModel.getImage());
            itemCartModel.setPrice_value(singleProductModel.getProduct_price());
            itemCartModel.setProduct_id(singleProductModel.getId());
            itemCartModel.setTitle(singleProductModel.getTitle());
            itemCartModel.setType(singleProductModel.getProduct_type());
            itemCartModel.setStock(singleProductModel.getStock_amount());

            singleProductModelList.add(itemCartModel);

        }
        newProductBillSellAdapter.notifyDataSetChanged();
        calculateTotal();
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

    public void CreateDialogAlert(Context context, int pos, String type, double stock) {
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .create();

        DialogInpiutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_inpiut, null, false);
        if(!type.equals("weight")){
            binding.edtwieght.setHint(getResources().getString(R.string.amount));
        }

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     String amount = binding.edtwieght.getText().toString();
                                                     if (!amount.isEmpty()) {
                                                         if(Double.parseDouble(amount)+singleProductModelList.get(pos).getAmount()<stock){
                                                         dialog.dismiss();
                                                         singleProductModelList.get(pos).setAmount2(Double.parseDouble(amount));
                                                         singleProductModelList.get(pos).setAmount(Double.parseDouble(amount) + singleProductModelList.get(pos).getAmount());
                                                         newProductBillSellAdapter.notifyItemChanged(pos);
                                                         calculateTotal();}
                                                         else{
                                                             Toast.makeText(NewBillOfSellActivity.this, getResources().getString(R.string.out_of_stock),Toast.LENGTH_LONG).show();
                                                         }
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

    public void CreateDialogConfirmAlert(Context context) {
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .create();

        DialogNewBillSellBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_new_bill_sell, null, false);
        taxamount = ((Double.parseDouble(taxamount) * total) / 100) + "";

        binding.spcoupon.setAdapter(spinnerDiscountAdapter);
        binding.spsuctomer.setAdapter(spinnercustomerAdapter);
        binding.setDate(System.currentTimeMillis());
        binding.tvTotal.setText((total) + "");
        binding.tvstay.setText((total + Double.parseDouble(taxamount) - discount-paid) + "");
        binding.tvdiscount.setText(discount + "");
        binding.tvtax.setText(taxamount);
        binding.btnaddcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addCustomers();
            }
        });
        binding.spcoupon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    coupon_id = 0;
                    discount = 0;
                    calculateTotal();
                } else {
                    coupon_id = singleDiscountModels.get(i).getId();
                    if (singleDiscountModels.get(i).getType().equals("per")) {
                        discount = (singleDiscountModels.get(i).getValue() * total) / 100;
                    } else {
                        discount = singleDiscountModels.get(i).getValue();
                    }
                    binding.tvstay.setText((total + Double.parseDouble(taxamount) - discount-paid) + "");
                    binding.tvdiscount.setText(discount + "");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createOrderModel.setClient_id(client_id);
                createOrderModel.setName(name);
                String date[];
                date = binding.tvdate.getText().toString().split("/");

                createOrderModel.setDate(date[2] + "-" + date[1] + "-" + date[0]);
                createOrderModel.setTotal_price(Math.round(total + Double.parseDouble(taxamount) - discount));
                createOrderModel.setPaid_price(Math.round(paid));
                createOrderModel.setRemaining_price(Math.round(total + Double.parseDouble(taxamount) - discount - paid));
                createOrderModel.setDiscount_value(discount);
                createOrderModel.setOrder_details(singleProductModelList);
                //  Log.e("llll",createOrderModel.getDiscount_value()+" "+createOrderModel.getRemaining_price()+" "+total);
                if (paymentModel.isDataValid(context)) {
                    dialog.dismiss();
                    presenter.checkData(paymentModel, createOrderModel, userModel);
                }

            }
        });
        binding.spsuctomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
Log.e("ldkkd",(total + Double.parseDouble(taxamount) - discount)+" "+paid);
                    if (paid <= (total + Double.parseDouble(taxamount) - discount)) {
                        Log.e("ldkkd",(total + Double.parseDouble(taxamount) - discount)+" "+paid);

                        binding.tvstay.setText((total + Double.parseDouble(taxamount) - discount-paid) + "");
                    } else {
                        binding.edtpaid.setError(getResources().getString(R.string.paid_must_small_or));
                    }
                } catch (Exception e) {
                    binding.tvstay.setText((total + Double.parseDouble(taxamount) - discount-paid) + "");
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
            presenter.getcustomer(userModel);
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
    public void ondiscountSuccess(AllDiscountsModel model) {
        if (lang.equals("en")) {
            singleDiscountModels.add(new SingleDiscountModel("choose Discount"));
        } else {

            singleDiscountModels.add(new SingleDiscountModel("اختر الخصم"));
        }
        //Log.e("dlldldl",model.getData().size()+"");
        singleDiscountModels.addAll(model.getData());
        spinnerDiscountAdapter = new SpinnerDiscountAdapter(singleDiscountModels, this);

    }

    @Override
    public void ondcustomerSuccess(AllCustomersModel model) {
        singleCustomerSuplliersModels.clear();
        if (lang.equals("en")) {

            singleCustomerSuplliersModels.add(new SingleCustomerSuplliersModel("Choose Customer"));
        } else {

            singleCustomerSuplliersModels.add(new SingleCustomerSuplliersModel("اختر عميل"));
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
    public void onCustomers() {
        Intent intent = new Intent(this, AddCustomerActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onsucess(BillModel body) {
        preferences.clearCart(this);
        Intent intent = new Intent(this, BillSellActivity.class);
        intent.putExtra("data", createOrderModel);
        intent.putExtra("databill", body);
        intent.putExtra("tax", taxamount);

        startActivity(intent);
        finish();

    }

}