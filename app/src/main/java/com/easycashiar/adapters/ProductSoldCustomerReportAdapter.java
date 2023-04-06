package com.easycashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.easycashiar.R;
import com.easycashiar.databinding.ProductSoldCustomerReportRowBinding;
import com.easycashiar.models.SalesPurchReportsModel;
import com.easycashiar.ui.activity_product_sold_customer_report.ProductSoldCustomerReportActivity;

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
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(context instanceof ProductSoldCustomerReportActivity){
            ProductSoldCustomerReportActivity activity=(ProductSoldCustomerReportActivity) context;
            activity.show(list.get(holder.getLayoutPosition()));
        }
    }
});
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
