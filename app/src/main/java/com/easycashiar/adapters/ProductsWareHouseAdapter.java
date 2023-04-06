package com.easycashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.easycashiar.R;
import com.easycashiar.databinding.ProductRowBinding;
import com.easycashiar.databinding.ProductWarehouseRowBinding;
import com.easycashiar.models.SingleProductModel;

import java.util.List;

public class ProductsWareHouseAdapter extends RecyclerView.Adapter<ProductsWareHouseAdapter.ProductsViewholder> {

    private List<SingleProductModel> list;
    private Context context;

    public ProductsWareHouseAdapter(Context context, List<SingleProductModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public ProductsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductWarehouseRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.product__warehouse_row, parent, false);
        return new ProductsViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewholder holder, int position) {

        holder.binding.setModel(list.get(position));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ProductsViewholder extends RecyclerView.ViewHolder {
        ProductWarehouseRowBinding binding;

        public ProductsViewholder(@NonNull ProductWarehouseRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
