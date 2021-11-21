package com.cashiar.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.cashiar.R;
import com.cashiar.databinding.SpinnerCategoryRowBinding;
import com.cashiar.databinding.SpinnerStockRowBinding;
import com.cashiar.models.SingleCategoryModel;
import com.cashiar.models.StockModel;

import java.util.List;

public class SpinnerStockAdapter extends BaseAdapter {
    private List<StockModel> list;
    private Context context;
    private LayoutInflater inflater;

    public SpinnerStockAdapter(List<StockModel> list, Context context) {
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
        @SuppressLint("ViewHolder") SpinnerStockRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.spinner_stock_row,parent,false);
        String title = list.get(position).getAr_title();
        binding.setTitle(title);
        return binding.getRoot();
    }
}
