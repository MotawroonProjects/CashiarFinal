package com.easycashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.easycashiar.R;
import com.easycashiar.databinding.CategoryRowBinding;
import com.easycashiar.databinding.StoreBalanceRowBinding;
import com.easycashiar.models.StoreBalanceModel;
import com.easycashiar.ui.activity_stock_balance.StockBalanceActivity;

import java.util.List;

public class StoreBalanceAdapter extends RecyclerView.Adapter<StoreBalanceAdapter.MyHolder> {

    private List<StoreBalanceModel> list;
    private Context context;
    private StockBalanceActivity activity;

    public StoreBalanceAdapter(Context context, List<StoreBalanceModel> list) {
        this.list = list;
        this.context = context;
        activity = (StockBalanceActivity) context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StoreBalanceRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.store_balance_row, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.binding.setModel(list.get(position));
        int pos = position+1;
        holder.binding.setPos(pos + "");
        holder.binding.imageEdit.setOnClickListener(v -> {
            activity.showChooseDialog(holder.getAdapterPosition(),list.get(holder.getAdapterPosition()));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyHolder extends RecyclerView.ViewHolder {
        StoreBalanceRowBinding binding;

        public MyHolder(@NonNull StoreBalanceRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
