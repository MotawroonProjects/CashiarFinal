package com.easycashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.easycashiar.R;
import com.easycashiar.databinding.SalesBillReportRowBinding;
import com.easycashiar.databinding.UnpaidSalesInvoicesReportRowBinding;
import com.easycashiar.models.SalesBillReportsModel;
import com.easycashiar.ui.activty_report_of_sales_bill.BillSalesReportActivity;

import java.util.List;

public class SalesBillReportAdapter extends RecyclerView.Adapter<SalesBillReportAdapter.SalesBillReportedViewholder> {

    private List<SalesBillReportsModel> list;
    private Context context;

    public SalesBillReportAdapter(Context context, List<SalesBillReportsModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public SalesBillReportedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SalesBillReportRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.sales_bill_report_row, parent, false);
        return new SalesBillReportedViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesBillReportedViewholder holder, int position) {
        holder.binding.setModel(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(context instanceof BillSalesReportActivity){
                    BillSalesReportActivity activity=(BillSalesReportActivity) context;
                activity.show(list.get(holder.getLayoutPosition()));}
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class SalesBillReportedViewholder extends RecyclerView.ViewHolder {
        SalesBillReportRowBinding binding;

        public SalesBillReportedViewholder(@NonNull SalesBillReportRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
