package com.cashiar.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cashiar.R;
import com.cashiar.databinding.ProductRowBinding;
import com.cashiar.databinding.ProductWarehouseRowBinding;
import com.cashiar.models.SingleProductModel;
import com.cashiar.ui.activity_products.ProductsActivity;
import com.cashiar.ui.activity_products_buy.ProductsBuyActivity;
import com.cashiar.ui.activity_products_sell.ProductsSellActivity;

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
