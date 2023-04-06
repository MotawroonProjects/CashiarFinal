package com.easycashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.easycashiar.R;
import com.easycashiar.databinding.PurchasesBillReportRowBinding;
import com.easycashiar.models.PurchasesBillReportsModel;

import java.util.List;

public class PurchasesBillReportAdapter extends RecyclerView.Adapter<PurchasesBillReportAdapter.PurchasesBillReportedViewholder> {

    private List<PurchasesBillReportsModel> list;
    private Context context;

    public PurchasesBillReportAdapter(Context context, List<PurchasesBillReportsModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public PurchasesBillReportedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PurchasesBillReportRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.purchases_bill_report_row, parent, false);
        return new PurchasesBillReportedViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasesBillReportedViewholder holder, int position) {
        holder.binding.setModel(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class PurchasesBillReportedViewholder extends RecyclerView.ViewHolder {
        PurchasesBillReportRowBinding binding;

        public PurchasesBillReportedViewholder(@NonNull PurchasesBillReportRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
