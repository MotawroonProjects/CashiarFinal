package com.easycashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.easycashiar.R;
import com.easycashiar.databinding.InventoryChangePurchasePriceForAnItemReportRowBinding;
import com.easycashiar.models.SalesPurchReportsModel;

import java.util.List;

public class InventoryInventoryChangePriceForItemReportAdapter extends RecyclerView.Adapter<InventoryInventoryChangePriceForItemReportAdapter.InventoryChangePurchasePriceForAnItem> {

    private List<SalesPurchReportsModel> list;
    private Context context;

    public InventoryInventoryChangePriceForItemReportAdapter(Context context, List<SalesPurchReportsModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public InventoryChangePurchasePriceForAnItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InventoryChangePurchasePriceForAnItemReportRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.inventory_change_purchase_price_for_an_item_report_row, parent, false);
        return new InventoryChangePurchasePriceForAnItem(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryChangePurchasePriceForAnItem holder, int position) {
        holder.binding.setModel(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class InventoryChangePurchasePriceForAnItem extends RecyclerView.ViewHolder {
        InventoryChangePurchasePriceForAnItemReportRowBinding binding;

        public InventoryChangePurchasePriceForAnItem(@NonNull InventoryChangePurchasePriceForAnItemReportRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
