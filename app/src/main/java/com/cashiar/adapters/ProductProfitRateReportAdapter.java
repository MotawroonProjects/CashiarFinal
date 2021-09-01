package com.cashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cashiar.R;
import com.cashiar.databinding.ProductProfitRateReportRowBinding;
import com.cashiar.models.SalesPurchReportsModel;

import java.util.List;

public class ProductProfitRateReportAdapter extends RecyclerView.Adapter<ProductProfitRateReportAdapter.ProductProfitRateReportedViewholder> {

    private List<SalesPurchReportsModel> list;
    private Context context;

    public ProductProfitRateReportAdapter(Context context, List<SalesPurchReportsModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public ProductProfitRateReportedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductProfitRateReportRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.product_profit_rate_report_row, parent, false);
        return new ProductProfitRateReportedViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductProfitRateReportedViewholder holder, int position) {
        holder.binding.setModel(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ProductProfitRateReportedViewholder extends RecyclerView.ViewHolder {
        ProductProfitRateReportRowBinding binding;

        public ProductProfitRateReportedViewholder(@NonNull ProductProfitRateReportRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
