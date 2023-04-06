package com.easycashiar.adapters;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.easycashiar.R;
import com.easycashiar.databinding.BluthoosRowBinding;
import com.easycashiar.ui.activity_bill_Sell.BillSellActivity;

import java.io.IOException;
import java.util.List;

public class BluthoosAdapter extends RecyclerView.Adapter<BluthoosAdapter.BluthoosViewholder> {

    private List<BluetoothDevice> list;
    private Context context;
    public String currency;

    public BluthoosAdapter(Context context, List<BluetoothDevice> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public BluthoosViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BluthoosRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.bluthoos_row, parent, false);
        return new BluthoosViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BluthoosViewholder holder, int position) {
        holder.binding.setTitle(list.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof BillSellActivity) {
                    BillSellActivity billSellActivity = (BillSellActivity) context;
                    try {
                        billSellActivity.openBT(list.get(position));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class BluthoosViewholder extends RecyclerView.ViewHolder {
        BluthoosRowBinding binding;

        public BluthoosViewholder(@NonNull BluthoosRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
