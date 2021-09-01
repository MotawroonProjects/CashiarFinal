package com.cashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cashiar.R;
import com.cashiar.databinding.ProductBillRowBinding;
import com.cashiar.databinding.ProductUnpaidBillRowBinding;
import com.cashiar.models.ItemCartModel;
import com.cashiar.models.SalesPurchReportsModel;

import java.util.List;

public class ProductUnpaidSellAdapter extends RecyclerView.Adapter<ProductUnpaidSellAdapter.ProductsViewholder> {

    public String currency;
    private List<SalesPurchReportsModel> list;
    private Context context;

    public ProductUnpaidSellAdapter(Context context, List<SalesPurchReportsModel> list, String currency) {
        this.list = list;
        this.context = context;
        this.currency = currency;

    }

    @NonNull
    @Override
    public ProductsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductUnpaidBillRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.product_unpaid_bill_row, parent, false);
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
        ProductUnpaidBillRowBinding binding;

        public ProductsViewholder(@NonNull ProductUnpaidBillRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
