package com.cashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cashiar.R;
import com.cashiar.databinding.SalesBillReportRowBinding;
import com.cashiar.databinding.UnpaidSalesInvoicesReportRowBinding;
import com.cashiar.models.SalesBillReportsModel;

import java.util.List;

public class SalesBillReportAdapter extends RecyclerView.Adapter<SalesBillReportAdapter.SalesBillReportedViewholder> {

    private List<SalesBillReportsModel> list;
    private Context context;

    public SalesBillReportAdapter(Context context, List<SalesBillReportsModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public SalesBillReportedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SalesBillReportRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.sales_bill_report_row, parent, false);
        return new SalesBillReportedViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesBillReportedViewholder holder, int position) {
        holder.binding.setModel(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class SalesBillReportedViewholder extends RecyclerView.ViewHolder {
        SalesBillReportRowBinding binding;

        public SalesBillReportedViewholder(@NonNull SalesBillReportRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
