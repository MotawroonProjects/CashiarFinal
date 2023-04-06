package com.easycashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.easycashiar.R;
import com.easycashiar.databinding.StockRowBinding;
import com.easycashiar.models.StockModel;
import com.easycashiar.ui.activity_stocks.StocksActivity;

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
        holder.binding.imageEdit.setOnClickListener(v -> {
            activity.editStock(list.get(holder.getAdapterPosition()));
        });
        holder.itemView.setOnClickListener(v -> {
            activity.showProducts(list.get(holder.getAdapterPosition()));
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
