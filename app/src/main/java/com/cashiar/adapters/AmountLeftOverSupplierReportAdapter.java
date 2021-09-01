package com.cashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cashiar.R;
import com.cashiar.databinding.AmountLeftOverForSupllierReportRowBinding;
import com.cashiar.models.AmountLeftOverReportsModel;

import java.util.List;

public class AmountLeftOverSupplierReportAdapter extends RecyclerView.Adapter<AmountLeftOverSupplierReportAdapter.AmountLeftOverForSupplierReportedViewholder> {

    private List<AmountLeftOverReportsModel> list;
    private Context context;

    public AmountLeftOverSupplierReportAdapter(Context context, List<AmountLeftOverReportsModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public AmountLeftOverForSupplierReportedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AmountLeftOverForSupllierReportRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.amount_left_over_for_supllier_report_row, parent, false);
        return new AmountLeftOverForSupplierReportedViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AmountLeftOverForSupplierReportedViewholder holder, int position) {
        holder.binding.setModel(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class AmountLeftOverForSupplierReportedViewholder extends RecyclerView.ViewHolder {
        AmountLeftOverForSupllierReportRowBinding binding;

        public AmountLeftOverForSupplierReportedViewholder(@NonNull AmountLeftOverForSupllierReportRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
