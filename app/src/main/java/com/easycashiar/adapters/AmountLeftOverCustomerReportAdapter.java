package com.easycashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.easycashiar.R;
import com.easycashiar.databinding.AmountLeftOverForCustomerReportRowBinding;
import com.easycashiar.models.AmountLeftOverReportsModel;

import java.util.List;

public class AmountLeftOverCustomerReportAdapter extends RecyclerView.Adapter<AmountLeftOverCustomerReportAdapter.AmountLeftOverForCustomerReportedViewholder> {

    private List<AmountLeftOverReportsModel> list;
    private Context context;

    public AmountLeftOverCustomerReportAdapter(Context context, List<AmountLeftOverReportsModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public AmountLeftOverForCustomerReportedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AmountLeftOverForCustomerReportRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.amount_left_over_for_customer_report_row, parent, false);
        return new AmountLeftOverForCustomerReportedViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AmountLeftOverForCustomerReportedViewholder holder, int position) {
        holder.binding.setModel(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class AmountLeftOverForCustomerReportedViewholder extends RecyclerView.ViewHolder {
        AmountLeftOverForCustomerReportRowBinding binding;

        public AmountLeftOverForCustomerReportedViewholder(@NonNull AmountLeftOverForCustomerReportRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
