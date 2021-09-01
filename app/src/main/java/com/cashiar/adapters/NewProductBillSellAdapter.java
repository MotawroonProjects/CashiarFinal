package com.cashiar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cashiar.R;
import com.cashiar.databinding.NewProductBillSellRowBinding;
import com.cashiar.databinding.ProductBillRowBinding;
import com.cashiar.models.ItemCartModel;
import com.cashiar.models.SingleProductModel;
import com.cashiar.ui.activity_new_bill_of_purchases.NewBillOfPurchasesActivity;
import com.cashiar.ui.activity_new_bill_of_sale.NewBillOfSellActivity;

import java.util.List;

public class NewProductBillSellAdapter extends RecyclerView.Adapter<NewProductBillSellAdapter.ProductsViewholder> {

    public String currency;
    private List<ItemCartModel> list;
    private Context context;

    public NewProductBillSellAdapter(Context context, List<ItemCartModel> list, String currency) {
        this.list = list;
        this.context = context;
        this.currency = currency;

    }

    @NonNull
    @Override
    public ProductsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewProductBillSellRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.new_product_bill_sell_row, parent, false);
        return new ProductsViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewholder holder, int position) {
        holder.binding.setCurrency(currency);
        holder.binding.setModel(list.get(position));
holder.binding.tvamount.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(context instanceof NewBillOfSellActivity){
            NewBillOfSellActivity newBillOfSellActivity=(NewBillOfSellActivity)context;
            if (list.get(holder.getLayoutPosition()).getType().equals("weight")) {
               // holder.binding.tvweight.setVisibility(View.VISIBLE);
                newBillOfSellActivity.CreateDialogAlert(context,holder.getLayoutPosition());
            }
            else {
            newBillOfSellActivity.increase(holder.getLayoutPosition());}
        }
        else    if(context instanceof NewBillOfPurchasesActivity) {
            NewBillOfPurchasesActivity newBillOfPurchasesActivity = (NewBillOfPurchasesActivity) context;
            if (list.get(holder.getLayoutPosition()).getType().equals("weight")) {
                // holder.binding.tvweight.setVisibility(View.VISIBLE);
                newBillOfPurchasesActivity.CreateDialogAlert(context, holder.getLayoutPosition());
            } else {
                newBillOfPurchasesActivity.increase(holder.getLayoutPosition());
            }
        }
    }


});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ProductsViewholder extends RecyclerView.ViewHolder {
        NewProductBillSellRowBinding binding;

        public ProductsViewholder(@NonNull NewProductBillSellRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
