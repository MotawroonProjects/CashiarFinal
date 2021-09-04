package com.cashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cashiar.R;
import com.cashiar.databinding.BillCustomerReportRowBinding;
import com.cashiar.models.CustomerBillReportsModel;
import com.cashiar.ui.activity_bill_customer.BillCustomerReportActivity;

import java.util.List;

public class CustomerBillReportAdapter extends RecyclerView.Adapter<CustomerBillReportAdapter.BillCustomerReportedViewholder> {

    private List<CustomerBillReportsModel> list;
    private Context context;

    public CustomerBillReportAdapter(Context context, List<CustomerBillReportsModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public BillCustomerReportedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BillCustomerReportRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.bill_customer_report_row, parent, false);
        return new BillCustomerReportedViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BillCustomerReportedViewholder holder, int position) {
        holder.binding.setModel(list.get(position));
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(context instanceof BillCustomerReportActivity){
            BillCustomerReportActivity activity=(BillCustomerReportActivity) context;
            activity.show(list.get(holder.getLayoutPosition()));
        }

    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class BillCustomerReportedViewholder extends RecyclerView.ViewHolder {
        BillCustomerReportRowBinding binding;

        public BillCustomerReportedViewholder(@NonNull BillCustomerReportRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
