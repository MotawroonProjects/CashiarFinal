package com.cashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cashiar.R;
import com.cashiar.databinding.ProductSoldCustomerReportRowBinding;
import com.cashiar.models.SalesPurchReportsModel;

import java.util.List;

public class ProductSoldCustomerReportAdapter extends RecyclerView.Adapter<ProductSoldCustomerReportAdapter.ProductSoldCustomerReportedViewholder> {

    private List<SalesPurchReportsModel> list;
    private Context context;

    public ProductSoldCustomerReportAdapter(Context context, List<SalesPurchReportsModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public ProductSoldCustomerReportedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductSoldCustomerReportRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.product_sold_customer_report_row, parent, false);
        return new ProductSoldCustomerReportedViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSoldCustomerReportedViewholder holder, int position) {
        holder.binding.setModel(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ProductSoldCustomerReportedViewholder extends RecyclerView.ViewHolder {
        ProductSoldCustomerReportRowBinding binding;

        public ProductSoldCustomerReportedViewholder(@NonNull ProductSoldCustomerReportRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
