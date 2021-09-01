package com.cashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cashiar.R;
import com.cashiar.databinding.DetailedPurchasesReportRowBinding;
import com.cashiar.databinding.DetailedSalesReportRowBinding;
import com.cashiar.models.SalesPurchReportsModel;

import java.util.List;

public class DetialedPurchasesReportAdapter extends RecyclerView.Adapter<DetialedPurchasesReportAdapter.DetaledSalesReportedViewholder> {

    private List<SalesPurchReportsModel> list;
    private Context context;

    public DetialedPurchasesReportAdapter(Context context, List<SalesPurchReportsModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public DetaledSalesReportedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DetailedPurchasesReportRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.detailed_purchases_report_row, parent, false);
        return new DetaledSalesReportedViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetaledSalesReportedViewholder holder, int position) {
        holder.binding.setModel(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class DetaledSalesReportedViewholder extends RecyclerView.ViewHolder {
        DetailedPurchasesReportRowBinding binding;

        public DetaledSalesReportedViewholder(@NonNull DetailedPurchasesReportRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
