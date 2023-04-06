package com.easycashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.easycashiar.R;
import com.easycashiar.databinding.UnpaidPurchasesInvoicesReportRowBinding;
import com.easycashiar.models.PurchasesBillReportsModel;

import java.util.List;

public class UnpaidPurchasesInvoicesReportAdapter extends RecyclerView.Adapter<UnpaidPurchasesInvoicesReportAdapter.UnpaidPurchaseInvoicesReportedViewholder> {

    private List<PurchasesBillReportsModel> list;
    private Context context;

    public UnpaidPurchasesInvoicesReportAdapter(Context context, List<PurchasesBillReportsModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public UnpaidPurchaseInvoicesReportedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UnpaidPurchasesInvoicesReportRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.unpaid_purchases_invoices_report_row, parent, false);
        return new UnpaidPurchaseInvoicesReportedViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UnpaidPurchaseInvoicesReportedViewholder holder, int position) {
        holder.binding.setModel(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class UnpaidPurchaseInvoicesReportedViewholder extends RecyclerView.ViewHolder {
        UnpaidPurchasesInvoicesReportRowBinding binding;

        public UnpaidPurchaseInvoicesReportedViewholder(@NonNull UnpaidPurchasesInvoicesReportRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
