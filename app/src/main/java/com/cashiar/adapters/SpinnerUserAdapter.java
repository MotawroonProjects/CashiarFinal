package com.cashiar.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.cashiar.R;
import com.cashiar.databinding.SpinnerStockRowBinding;
import com.cashiar.databinding.SpinnerUsersRowBinding;
import com.cashiar.models.StockModel;
import com.cashiar.models.UserModel;

import java.util.List;

public class SpinnerUserAdapter extends BaseAdapter {
    private List<UserModel> list;
    private Context context;
    private LayoutInflater inflater;

    public SpinnerUserAdapter(List<UserModel> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") SpinnerUsersRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.spinner_users_row,parent,false);
        String title = list.get(position).getName();
        binding.setTitle(title);
        return binding.getRoot();
    }
}
