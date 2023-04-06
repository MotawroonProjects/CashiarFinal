package com.easycashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.easycashiar.R;
import com.easycashiar.databinding.StoreInventoryReportRowBinding;
import com.easycashiar.models.SalesPurchReportsModel;

import java.util.List;

public class StoreInventoryReportAdapter extends RecyclerView.Adapter<StoreInventoryReportAdapter.StoreInventoryReportedViewholder> {

    private List<SalesPurchReportsModel> list;
    private Context context;

    public StoreInventoryReportAdapter(Context context, List<SalesPurchReportsModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public StoreInventoryReportedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StoreInventoryReportRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.store_inventory_report_row, parent, false);
        return new StoreInventoryReportedViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreInventoryReportedViewholder holder, int position) {
        holder.binding.setModel(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class StoreInventoryReportedViewholder extends RecyclerView.ViewHolder {
        StoreInventoryReportRowBinding binding;

        public StoreInventoryReportedViewholder(@NonNull StoreInventoryReportRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
