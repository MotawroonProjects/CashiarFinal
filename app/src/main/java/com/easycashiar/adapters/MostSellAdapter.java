package com.easycashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.easycashiar.R;
import com.easycashiar.databinding.MostsaleproductRowBinding;
import com.easycashiar.databinding.ProductBillRowBinding;
import com.easycashiar.models.SingleProductModel;

import java.util.List;

public class MostSellAdapter extends RecyclerView.Adapter<MostSellAdapter.ProductsViewholder> {

    public String currency;
    private List<SingleProductModel> list;
    private Context context;

    public MostSellAdapter(Context context, List<SingleProductModel> list) {
        this.list = list;
        this.context = context;
        this.currency = currency;

    }

    @NonNull
    @Override
    public ProductsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MostsaleproductRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.mostsaleproduct_row, parent, false);
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
        MostsaleproductRowBinding binding;

        public ProductsViewholder(@NonNull MostsaleproductRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
