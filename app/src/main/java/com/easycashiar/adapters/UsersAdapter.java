package com.easycashiar.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.easycashiar.R;
import com.easycashiar.databinding.CustomerRowBinding;
import com.easycashiar.databinding.UserRowBinding;
import com.easycashiar.models.UserModel;
import com.easycashiar.ui.activity_premission_stock.PremissionStockActivity;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.CustomerViewholder> {

    private List<UserModel> list;
    private Context context;

    public UsersAdapter(Context context, List<UserModel> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.user_row, parent, false);
        return new CustomerViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.setModel(list.get(position));
        holder.binding.imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if (context instanceof PremissionStockActivity) {
                    PremissionStockActivity premissionStockActivity = (PremissionStockActivity) context;
                    premissionStockActivity.onitemselect(list.get(position),"add");
                }
            }
        });
        holder.binding.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof PremissionStockActivity) {
                    PremissionStockActivity premissionStockActivity = (PremissionStockActivity) context;
                    premissionStockActivity.onitemselect(list.get(position),"delete");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class CustomerViewholder extends RecyclerView.ViewHolder {
        UserRowBinding binding;

        public CustomerViewholder(@NonNull UserRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
