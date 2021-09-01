package com.cashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cashiar.R;
import com.cashiar.databinding.DetialedExpenseReportRowBinding;
import com.cashiar.models.SingleExpensesModel;

import java.util.List;

public class DetialedExpensesReportAdapter extends RecyclerView.Adapter<DetialedExpensesReportAdapter.DetialedExpenseReportReportedViewholder> {

    private List<SingleExpensesModel> list;
    private Context context;

    public DetialedExpensesReportAdapter(Context context, List<SingleExpensesModel> list) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public DetialedExpenseReportReportedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DetialedExpenseReportRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.detialed_expense_report_row, parent, false);
        return new DetialedExpenseReportReportedViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetialedExpenseReportReportedViewholder holder, int position) {
        holder.binding.setModel(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class DetialedExpenseReportReportedViewholder extends RecyclerView.ViewHolder {
        DetialedExpenseReportRowBinding binding;

        public DetialedExpenseReportReportedViewholder(@NonNull DetialedExpenseReportRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
