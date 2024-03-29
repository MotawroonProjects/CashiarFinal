package com.easycashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.easycashiar.R;
import com.easycashiar.databinding.AggreateSalesPurchasesReportRowBinding;
import com.easycashiar.models.SalesPurchReportsModel;
import com.easycashiar.ui.activity_aggerate_sales_report.AggreateSalesReportActivity;

import java.util.List;

public class AggreateSalesReportAdapter extends RecyclerView.Adapter<AggreateSalesReportAdapter.AggreateSalesPurchasesReportedViewholder> {

    private List<SalesPurchReportsModel> list;
    private Context context;

    public AggreateSalesReportAdapter(Context context, List<SalesPurchReportsModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public AggreateSalesPurchasesReportedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AggreateSalesPurchasesReportRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.aggreate_sales_purchases_report_row, parent, false);
        return new AggreateSalesPurchasesReportedViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AggreateSalesPurchasesReportedViewholder holder, int position) {
        holder.binding.setModel(list.get(position));
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(context instanceof AggreateSalesReportActivity){
            AggreateSalesReportActivity activity=(AggreateSalesReportActivity) context;
            activity.show(list.get(holder.getLayoutPosition()));
        }
    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class AggreateSalesPurchasesReportedViewholder extends RecyclerView.ViewHolder {
        AggreateSalesPurchasesReportRowBinding binding;

        public AggreateSalesPurchasesReportedViewholder(@NonNull AggreateSalesPurchasesReportRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
