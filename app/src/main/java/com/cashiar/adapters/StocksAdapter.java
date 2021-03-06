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
import com.cashiar.databinding.StockRowBinding;
import com.cashiar.models.StockModel;
import com.cashiar.ui.activity_categories.CategoriesActivity;
import com.cashiar.ui.activity_stocks.StocksActivity;

import java.util.List;

public class StocksAdapter extends RecyclerView.Adapter<StocksAdapter.MyHolder> {

    private List<StockModel> list;
    private Context context;
    private StocksActivity activity;

    public StocksAdapter(Context context, List<StockModel> list) {
        this.list = list;
        this.context = context;
        activity = (StocksActivity) context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StockRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.stock_row, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.binding.setModel(list.get(position));
        holder.itemView.setOnClickListener(v -> {
            activity.editStock(list.get(holder.getAdapterPosition()));
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyHolder extends RecyclerView.ViewHolder {
        StockRowBinding binding;

        public MyHolder(@NonNull StockRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
