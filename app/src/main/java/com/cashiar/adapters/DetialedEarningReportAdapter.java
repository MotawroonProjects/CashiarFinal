package com.cashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cashiar.R;
import com.cashiar.databinding.DetailedEarnReportRowBinding;
import com.cashiar.models.SalesPurchReportsModel;

import java.util.List;

public class DetialedEarningReportAdapter extends RecyclerView.Adapter<DetialedEarningReportAdapter.DetaledEarningReportedViewholder> {

    private List<SalesPurchReportsModel> list;
    private Context context;

    public DetialedEarningReportAdapter(Context context, List<SalesPurchReportsModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public DetaledEarningReportedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DetailedEarnReportRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.detailed_earn_report_row, parent, false);
        return new DetaledEarningReportedViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetaledEarningReportedViewholder holder, int position) {
        holder.binding.setModel(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class DetaledEarningReportedViewholder extends RecyclerView.ViewHolder {
        DetailedEarnReportRowBinding binding;

        public DetaledEarningReportedViewholder(@NonNull DetailedEarnReportRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
