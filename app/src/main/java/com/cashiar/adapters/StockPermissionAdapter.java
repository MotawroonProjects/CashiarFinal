package com.cashiar.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cashiar.R;
import com.cashiar.databinding.PermissionRowBinding;
import com.cashiar.databinding.StockPermissionRowBinding;
import com.cashiar.models.SinglePermissionModel;
import com.cashiar.models.StockModel;
import com.cashiar.ui.activity_add_cashier.AddCashierActivity;
import com.cashiar.ui.activity_add_delete_premission.AddDeletePremissionActivity;

import java.util.List;

public class StockPermissionAdapter extends RecyclerView.Adapter<StockPermissionAdapter.StockPermessionViewholder> {

    private List<StockModel> list;
    private Context context;
    private AddDeletePremissionActivity addDeletePremissionActivity;

    public StockPermissionAdapter(Context context, List<StockModel> list) {
        this.list = list;
        this.context = context;
        addDeletePremissionActivity = (AddDeletePremissionActivity) context;
    }

    @NonNull
    @Override
    public StockPermessionViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StockPermissionRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.stock_permission_row, parent, false);
        return new StockPermessionViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StockPermessionViewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.setModel(list.get(position));
        holder.binding.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    addDeletePremissionActivity.addid(list.get(position).getId());
                } else {
                    addDeletePremissionActivity.removeid(list.get(position).getId());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class StockPermessionViewholder extends RecyclerView.ViewHolder {
        StockPermissionRowBinding binding;

        public StockPermessionViewholder(@NonNull StockPermissionRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
