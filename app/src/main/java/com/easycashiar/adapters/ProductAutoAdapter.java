package com.easycashiar.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.easycashiar.R;
import com.easycashiar.databinding.ProductAutoRowBinding;
import com.easycashiar.models.SingleProductModel;
import com.easycashiar.ui.activity_new_bill_of_purchases.NewBillOfPurchasesActivity;
import com.easycashiar.ui.activity_new_bill_of_sale.NewBillOfSellActivity;

import java.util.List;

public class ProductAutoAdapter extends ArrayAdapter<SingleProductModel> {
    private final Context mContext;
    private final List<SingleProductModel> singleProductModelList;
    private final int mLayoutResourceId;

    public ProductAutoAdapter(Context context, int resource, List<SingleProductModel> singleProductModelList) {
        super(context, resource, singleProductModelList);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.singleProductModelList=singleProductModelList;
    }

    public int getCount() {
        return singleProductModelList.size();
    }

    public SingleProductModel getItem(int position) {
        return singleProductModelList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(mLayoutResourceId, parent, false);
            }
            SingleProductModel singleProductModel = getItem(position);
            TextView name = (TextView) convertView.findViewById(R.id.tvTitle);
            name.setText(singleProductModel.getTitle());
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mContext instanceof NewBillOfSellActivity){
                        if(singleProductModel.getwarehouse_stock()>0) {
                            NewBillOfSellActivity newBillOfSellActivity = (NewBillOfSellActivity) mContext;
                            newBillOfSellActivity.additem(singleProductModelList.get(position));
                        }
                        else{
                            Toast.makeText(mContext, mContext.getResources().getString(R.string.out_of_stock),Toast.LENGTH_LONG).show();
                        }
                    }
                   else if(mContext instanceof NewBillOfPurchasesActivity){

                        NewBillOfPurchasesActivity newBillOfPurchasesActivity=(NewBillOfPurchasesActivity) mContext;
                        newBillOfPurchasesActivity.additem(singleProductModelList.get(position));

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }}