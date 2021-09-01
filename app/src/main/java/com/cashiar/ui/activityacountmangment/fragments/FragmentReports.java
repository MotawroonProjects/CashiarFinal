package com.cashiar.ui.activityacountmangment.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.cashiar.R;
import com.cashiar.databinding.DialogNameBinding;
import com.cashiar.databinding.FragmentReportsBinding;
import com.cashiar.mvp.fragment_report_mvp.FragmentReportPresenter;
import com.cashiar.mvp.fragment_report_mvp.ReportFragmentView;
import com.cashiar.preferences.Preferences;
import com.cashiar.ui.activity_aggerate_expenses_report.AggerateExpenseReportActivity;
import com.cashiar.ui.activity_aggerate_purchases_report.AggreatePurchasesReportActivity;
import com.cashiar.ui.activity_aggerate_sales_report.AggreateSalesReportActivity;
import com.cashiar.ui.activity_amount_left_over_customer.AmountLeftOverCustomerCustomerReportActivity;
import com.cashiar.ui.activity_amount_left_over_supplier.AmountLeftOverSupplierReportActivity;
import com.cashiar.ui.activity_bill_customer.BillCustomerReportActivity;
import com.cashiar.ui.activity_bill_supplier.BillSupplierReportActivity;
import com.cashiar.ui.activity_detailed_expenses_report.DetailedExpenseReportActivity;
import com.cashiar.ui.activity_earn_detailed_report.DetailedEarnReportActivity;
import com.cashiar.ui.activity_inventory_change_purchases_price_for_an_item_report.InventoryChangePurchasesPriceForanItemReportActivity;
import com.cashiar.ui.activity_product_profit_rate_report.ProductProfitReateReportActivity;
import com.cashiar.ui.activity_product_purchases_supplier_report.ProductPurchasesSupplierReportActivity;
import com.cashiar.ui.activity_product_sold_customer_report.ProductSoldCustomerReportActivity;
import com.cashiar.ui.activity_report_of_unpaid_purchases_invoices.UnpaidPurchasesInvoicesReportActivity;
import com.cashiar.ui.activity_report_of_unpaid_sales_invoices.UnpaidSalesInvoicesReportActivity;
import com.cashiar.ui.activity_store_inventory_report.StoreInventoryReportActivity;
import com.cashiar.ui.activityacountmangment.AccountMangmentActivity;
import com.cashiar.ui.activty_aggreate_earn_report.AggreateEarnReportActivity;
import com.cashiar.ui.activty_report_of_purchases_bill.BillPurchasesReportActivity;
import com.cashiar.ui.activty_report_of_sales_bill.BillSalesReportActivity;
import com.cashiar.ui.activity_detailed_purchases_report.DetailedPurchasesReportActivity;
import com.cashiar.ui.activity_detailed_sales_report.DetailedSalesReportActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FragmentReports extends Fragment implements ReportFragmentView {
    private AccountMangmentActivity activity;
    private FragmentReportsBinding binding;
    private Preferences preferences;
    private FragmentReportPresenter presenter;

    private int pos;
    private String type;
    private String type1, type2;
    private String str = "all", end = "all";

    public static FragmentReports newInstance() {
        return new FragmentReports();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reports, container, false);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        preferences = Preferences.getInstance();
        activity = (AccountMangmentActivity) getActivity();
        presenter = new FragmentReportPresenter(this, activity);

        type1 = activity.getResources().getString(R.string.day);
        type2 = activity.getResources().getString(R.string.day);
        String date = dateFormat.format(new Date(System.currentTimeMillis()));
        str = date;
        end = date;
        binding.tvsalesdetialedReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailedSalesReportActivity.class);
                intent.putExtra("str", str);
                intent.putExtra("end", end);
                startActivity(intent);
            }
        });
        binding.tvaggreatesalesreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AggreateSalesReportActivity.class);
                intent.putExtra("str", str);
                intent.putExtra("end", end);
                startActivity(intent);
            }
        });
        binding.tvunpaidsalesbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UnpaidSalesInvoicesReportActivity.class);
                intent.putExtra("str", str);
                intent.putExtra("end", end);
                startActivity(intent);
            }
        });
        binding.tvsalesbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, BillSalesReportActivity.class);
                intent.putExtra("str", str);
                intent.putExtra("end", end);
                startActivity(intent);
            }
        });
        binding.tvdetialdearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailedEarnReportActivity.class);
                intent.putExtra("str", str);
                intent.putExtra("end", end);
                startActivity(intent);
            }
        });
        binding.tvaggreateearnreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AggreateEarnReportActivity.class);
                intent.putExtra("str", str);
                intent.putExtra("end", end);
                startActivity(intent);
            }
        });
        binding.tvproductprofitrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ProductProfitReateReportActivity.class);
                intent.putExtra("str", str);
                intent.putExtra("end", end);
                startActivity(intent);
            }
        });
        binding.tvamountleftovercustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AmountLeftOverCustomerCustomerReportActivity.class);
                intent.putExtra("str", str);
                intent.putExtra("end", end);
                startActivity(intent);
            }
        });
        binding.tvclientbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDialogAlert(activity,1);
            }
        });
        binding.tvproductsoldcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDialogAlert(activity,2);
            }
        });
        binding.tvamountleftoversupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AmountLeftOverSupplierReportActivity.class);
                intent.putExtra("str", str);
                intent.putExtra("end", end);
                startActivity(intent);
            }
        });
        binding.tvbillsupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDialogAlert(activity,3);
            }
        });
        binding.tvproductpurchasessupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDialogAlert(activity,4);
            }
        });
        binding.tvdetialdpurchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailedPurchasesReportActivity.class);
                intent.putExtra("str", str);
                intent.putExtra("end", end);
                startActivity(intent);
            }
        });
        binding.tvaggreatepurchasesreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AggreatePurchasesReportActivity.class);
                intent.putExtra("str", str);
                intent.putExtra("end", end);
                startActivity(intent);
            }
        });
        binding.tvunpaidpurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UnpaidPurchasesInvoicesReportActivity.class);
                intent.putExtra("str", str);
                intent.putExtra("end", end);
                startActivity(intent);
            }
        });
        binding.tvpurchasesbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, BillPurchasesReportActivity.class);
                intent.putExtra("str", str);
                intent.putExtra("end", end);
                startActivity(intent);
            }
        });
        binding.tvstoreinventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, StoreInventoryReportActivity.class);
                intent.putExtra("str", str);
                intent.putExtra("end", end);
                startActivity(intent);
            }
        });
        binding.tvinventorychange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDialogAlert(activity,5);
            }
        });
        binding.tvdetialedexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetailedExpenseReportActivity.class);
                intent.putExtra("str", str);
                intent.putExtra("end", end);
                startActivity(intent);
            }
        });
        binding.tvaggreatexpensereport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AggerateExpenseReportActivity.class);
                intent.putExtra("str", str);
                intent.putExtra("end", end);
                startActivity(intent);
            }
        });
        binding.fl.setOnClickListener(view -> openSheet());
        binding.tvday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvday.setText(activity.getResources().getString(R.string.day));
                closeSheet();
                type = "today";
                String date = dateFormat.format(new Date(System.currentTimeMillis()));
                str = date;
                end = date;
                Log.e("ldlldl", str + " " + end);

                if (pos == 0) {
                    type1 = activity.getResources().getString(R.string.day);
                } else {
                    type2 = activity.getResources().getString(R.string.day);
                }

            }
        });
        binding.tvthismonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(activity.getResources().getString(R.string.this_month));
                closeSheet();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(System.currentTimeMillis()));
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
                str = dateFormat.format(calendar.getTime());
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                end = dateFormat.format(calendar.getTime());
                Log.e("ldlldl", str + " " + end);
                type = "this_month";

                if (pos == 0) {
                    type1 = activity.getResources().getString(R.string.this_month);
                } else {
                    type2 = activity.getResources().getString(R.string.this_month);
                }
            }
        });
        binding.tvlsevday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(activity.getResources().getString(R.string.last_seven_day));
                closeSheet();
                type = "last7days";
                String date = dateFormat.format(new Date(System.currentTimeMillis()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(System.currentTimeMillis()));
                calendar.add(Calendar.DATE, -6);
                str = dateFormat.format(calendar.getTime());
                end = date;
                Log.e("ldlldl", str + " " + end);

                if (pos == 0) {
                    type1 = activity.getResources().getString(R.string.last_seven_day);
                } else {
                    type2 = activity.getResources().getString(R.string.last_seven_day);
                }

            }
        });
        binding.tvextentofwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(activity.getResources().getString(R.string.extent_of_work));
                closeSheet();
                type = "all";
                str = "all";
                end = "all";
                if (pos == 0) {
                    type1 = activity.getResources().getString(R.string.extent_of_work);
                } else {
                    type2 = activity.getResources().getString(R.string.extent_of_work);
                }

            }
        });
        binding.tvlmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(activity.getResources().getString(R.string.last_month));
                closeSheet();
                type = "last_month";
                String date = dateFormat.format(new Date(System.currentTimeMillis()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(System.currentTimeMillis()));
                calendar.add(Calendar.DATE, -29);
                str = dateFormat.format(calendar.getTime());
                end = date;
                Log.e("ldlldl", str + " " + end);
                if (pos == 0) {
                    type1 = activity.getResources().getString(R.string.last_month);
                } else {
                    type2 = activity.getResources().getString(R.string.last_month);
                }

            }
        });
        binding.tvlthrityday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(activity.getResources().getString(R.string.last_thirty_day));
                closeSheet();
                type = "last30days";
                String date = dateFormat.format(new Date(System.currentTimeMillis()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(System.currentTimeMillis()));
                calendar.add(Calendar.DATE, -29);
                str = dateFormat.format(calendar.getTime());
                end = date;
                Log.e("ldlldl", str + " " + end);

                if (pos == 0) {
                    type1 = activity.getResources().getString(R.string.last_thirty_day);
                } else {
                    type2 = activity.getResources().getString(R.string.last_thirty_day);
                }


            }
        });
        binding.tvyday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(activity.getResources().getString(R.string.yesterday));
                closeSheet();
                String date = dateFormat.format(new Date(System.currentTimeMillis()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(System.currentTimeMillis()));
                calendar.add(Calendar.DATE, -1);
                str = dateFormat.format(calendar.getTime());
                end = str;
                Log.e("ldlldl", str + " " + end);

                type = "yesterday";
                if (pos == 0) {
                    type1 = activity.getResources().getString(R.string.yesterday);
                } else {
                    type2 = activity.getResources().getString(R.string.yesterday);
                }


            }
        });
        binding.tvcustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tv.setText(activity.getResources().getString(R.string.custom_history));
                closeSheet();
                type = "custom";
                presenter.show(activity.getFragmentManager(), 1);

                if (pos == 0) {
                    type1 = activity.getResources().getString(R.string.custom_history);
                } else {
                    type2 = activity.getResources().getString(R.string.custom_history);
                }

            }
        });
        binding.llsales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.expandLayoutsales.isExpanded()) {
                    binding.expandLayoutsales.collapse(true);
                } else {
                    binding.expandLayoutsales.expand(true);
                }
                binding.expandLayoutcustomers.collapse(true);
                binding.expandLayoutexpense.collapse(true);
                binding.expandLayoutprofit.collapse(true);
                binding.expandLayoutpurchases.collapse(true);
                binding.expandLayoutsuppliers.collapse(true);
                binding.expandLayoutstore.collapse(true);


            }
        });
        binding.llcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.expandLayoutcustomers.isExpanded()) {
                    binding.expandLayoutcustomers.collapse(true);
                } else {
                    binding.expandLayoutcustomers.expand(true);
                }
                binding.expandLayoutsales.collapse(true);
                binding.expandLayoutexpense.collapse(true);
                binding.expandLayoutprofit.collapse(true);
                binding.expandLayoutpurchases.collapse(true);
                binding.expandLayoutsuppliers.collapse(true);
                binding.expandLayoutstore.collapse(true);


            }
        });
        binding.llExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.expandLayoutexpense.isExpanded()) {
                    binding.expandLayoutexpense.collapse(true);
                } else {
                    binding.expandLayoutexpense.expand(true);
                }
                binding.expandLayoutcustomers.collapse(true);
                binding.expandLayoutsales.collapse(true);
                binding.expandLayoutprofit.collapse(true);
                binding.expandLayoutpurchases.collapse(true);
                binding.expandLayoutsuppliers.collapse(true);
                binding.expandLayoutstore.collapse(true);


            }
        });
        binding.llprofits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.expandLayoutprofit.isExpanded()) {
                    binding.expandLayoutprofit.collapse(true);
                } else {
                    binding.expandLayoutprofit.expand(true);
                }
                binding.expandLayoutcustomers.collapse(true);
                binding.expandLayoutexpense.collapse(true);
                binding.expandLayoutsales.collapse(true);
                binding.expandLayoutpurchases.collapse(true);
                binding.expandLayoutsuppliers.collapse(true);
                binding.expandLayoutstore.collapse(true);


            }
        });
        binding.llpurchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.expandLayoutpurchases.isExpanded()) {
                    binding.expandLayoutpurchases.collapse(true);
                } else {
                    binding.expandLayoutpurchases.expand(true);
                }
                binding.expandLayoutcustomers.collapse(true);
                binding.expandLayoutexpense.collapse(true);
                binding.expandLayoutprofit.collapse(true);
                binding.expandLayoutsales.collapse(true);
                binding.expandLayoutsuppliers.collapse(true);
                binding.expandLayoutstore.collapse(true);


            }
        });
        binding.llSupliers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.expandLayoutsuppliers.isExpanded()) {
                    binding.expandLayoutsuppliers.collapse(true);
                } else {
                    binding.expandLayoutsuppliers.expand(true);
                }
                binding.expandLayoutcustomers.collapse(true);
                binding.expandLayoutexpense.collapse(true);
                binding.expandLayoutprofit.collapse(true);
                binding.expandLayoutpurchases.collapse(true);
                binding.expandLayoutsales.collapse(true);
                binding.expandLayoutstore.collapse(true);


            }
        });
        binding.llstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.expandLayoutstore.isExpanded()) {
                    binding.expandLayoutstore.collapse(true);
                } else {
                    binding.expandLayoutstore.expand(true);
                }
                binding.expandLayoutcustomers.collapse(true);
                binding.expandLayoutexpense.collapse(true);
                binding.expandLayoutprofit.collapse(true);
                binding.expandLayoutpurchases.collapse(true);
                binding.expandLayoutsuppliers.collapse(true);
                binding.expandLayoutsales.collapse(true);


            }
        });
    }


    private void openSheet() {
        Animation animation = AnimationUtils.loadAnimation(activity, R.anim.slide_up);


        binding.flfilter.clearAnimation();
        binding.flfilter.startAnimation(animation);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.flfilter.setVisibility(View.VISIBLE);


            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void closeSheet() {
        Animation animation = AnimationUtils.loadAnimation(activity, R.anim.slide_down);


        binding.flfilter.clearAnimation();
        binding.flfilter.startAnimation(animation);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                binding.flfilter.setVisibility(View.GONE);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    public void onDateSelected(String date, int type) {
        if (type == 1) {
            str = date;
            presenter.show(activity.getFragmentManager(), 2);
        } else {
            end = date;
        }

    }

    public void CreateDialogAlert(Context context, int type) {
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .create();

        DialogNameBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_name, null, false);
if(type==5){
    binding.edtname.setHint(activity.getResources().getString(R.string.enter_product_name));
}

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     String name = binding.edtname.getText().toString();
                                                     if (!name.isEmpty()) {
                                                         dialog.dismiss();
                                                         Intent intent;
                                                         if (type == 1) {
                                                             intent = new Intent(activity, BillCustomerReportActivity.class);
                                                         } else if (type==2){
                                                             intent = new Intent(activity, ProductSoldCustomerReportActivity.class);

                                                         }
                                                         else if (type==3){
                                                             intent = new Intent(activity, BillSupplierReportActivity.class);

                                                         }
                                                         else if(type==4){
                                                             intent = new Intent(activity, ProductPurchasesSupplierReportActivity.class);

                                                         }
                                                         else {
                                                             intent = new Intent(activity, InventoryChangePurchasesPriceForanItemReportActivity.class);

                                                         }
                                                         intent.putExtra("str", str);
                                                         intent.putExtra("end", end);
                                                         intent.putExtra("name", name);

                                                         startActivity(intent);

                                                     } else {
                                                         binding.edtname.setError(context.getResources().getString(R.string.field_required));
                                                     }
                                                 }
                                             }

        );
        dialog.getWindow().getAttributes().windowAnimations = R.style.Theme_App;
        dialog.setCanceledOnTouchOutside(false);
        dialog.setView(binding.getRoot());
        dialog.show();
    }

}
