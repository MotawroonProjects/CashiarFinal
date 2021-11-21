package com.cashiar.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cashiar.R;
import com.cashiar.databinding.CategoryRowBinding;
import com.cashiar.databinding.StoreBalanceRowBinding;
import com.cashiar.models.StoreBalanceModel;
import com.cashiar.ui.activity_categories.CategoriesActivity;

import java.util.List;

public class StoreBalanceAdapter extends RecyclerView.Adapter<StoreBalanceAdapter.MyHolder> {

    private List<StoreBalanceModel> list;
    private Context context;

    public StoreBalanceAdapter(Context context, List<StoreBalanceModel> list) {
        this.list = list;
        this.context = context;
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
        holder.binding.setPos((position + 1) + "");
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
