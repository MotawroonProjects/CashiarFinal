package com.easycashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.easycashiar.R;
import com.easycashiar.databinding.ProductBillRowBinding;
import com.easycashiar.databinding.ProductRowBinding;
import com.easycashiar.models.ItemCartModel;

import java.util.List;

public class ProductsSellAdapter extends RecyclerView.Adapter<ProductsSellAdapter.ProductsViewholder> {

    public String currency;
    private List<ItemCartModel> list;
    private Context context;

    public ProductsSellAdapter(Context context, List<ItemCartModel> list, String currency) {
        this.list = list;
        this.context = context;
        this.currency = currency;

    }

    @NonNull
    @Override
    public ProductsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductBillRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.product_bill_row, parent, false);
        return new ProductsViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewholder holder, int position) {
        holder.binding.setCurrency(currency);
        holder.binding.setModel(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ProductsViewholder extends RecyclerView.ViewHolder {
        ProductBillRowBinding binding;

        public ProductsViewholder(@NonNull ProductBillRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
