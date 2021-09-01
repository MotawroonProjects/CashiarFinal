package com.cashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cashiar.R;
import com.cashiar.databinding.DetailedSalesReportRowBinding;
import com.cashiar.databinding.ProductBillRowBinding;
import com.cashiar.models.SalesPurchReportsModel;
import com.cashiar.ui.activity_detailed_sales_report.DetailedSalesReportActivity;

import java.util.List;

public class DetialedSalesReportAdapter extends RecyclerView.Adapter<DetialedSalesReportAdapter.DetaledSalesReportedViewholder> {

    private List<SalesPurchReportsModel> list;
    private Context context;

    public DetialedSalesReportAdapter(Context context, List<SalesPurchReportsModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public DetaledSalesReportedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DetailedSalesReportRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.detailed_sales_report_row, parent, false);
        return new DetaledSalesReportedViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetaledSalesReportedViewholder holder, int position) {
        holder.binding.setModel(list.get(position));
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(context instanceof DetailedSalesReportActivity){
            DetailedSalesReportActivity activity=(DetailedSalesReportActivity) context;
            activity.show(list.get(holder.getLayoutPosition()));
        }
    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class DetaledSalesReportedViewholder extends RecyclerView.ViewHolder {
        DetailedSalesReportRowBinding binding;

        public DetaledSalesReportedViewholder(@NonNull DetailedSalesReportRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
