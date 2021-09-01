package com.cashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cashiar.R;
import com.cashiar.databinding.UnpaidSalesInvoicesReportRowBinding;
import com.cashiar.models.SalesBillReportsModel;
import com.cashiar.models.SalesPurchReportsModel;
import com.cashiar.ui.activity_report_of_unpaid_sales_invoices.UnpaidSalesInvoicesReportActivity;

import java.util.List;

public class UnpaidSalesInvoicesReportAdapter extends RecyclerView.Adapter<UnpaidSalesInvoicesReportAdapter.UnpaidSalesInvoicesReportedViewholder> {

    private List<SalesBillReportsModel> list;
    private Context context;

    public UnpaidSalesInvoicesReportAdapter(Context context, List<SalesBillReportsModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public UnpaidSalesInvoicesReportedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UnpaidSalesInvoicesReportRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.unpaid_sales_invoices_report_row, parent, false);
        return new UnpaidSalesInvoicesReportedViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UnpaidSalesInvoicesReportedViewholder holder, int position) {
        holder.binding.setModel(list.get(position));
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(context instanceof UnpaidSalesInvoicesReportActivity){
            UnpaidSalesInvoicesReportActivity  unpaidSalesInvoicesReportActivity=(UnpaidSalesInvoicesReportActivity)context;
            unpaidSalesInvoicesReportActivity.payinvoice(list.get(holder.getLayoutPosition()));
        }
    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class UnpaidSalesInvoicesReportedViewholder extends RecyclerView.ViewHolder {
        UnpaidSalesInvoicesReportRowBinding binding;

        public UnpaidSalesInvoicesReportedViewholder(@NonNull UnpaidSalesInvoicesReportRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
