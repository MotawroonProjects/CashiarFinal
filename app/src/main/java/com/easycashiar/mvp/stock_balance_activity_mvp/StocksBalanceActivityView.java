package com.easycashiar.mvp.stock_balance_activity_mvp;

import com.easycashiar.models.SingleProductModel;
import com.easycashiar.models.StockModel;

import java.util.List;

public interface StocksBalanceActivityView {

    void onStocksSuccess(List<StockModel> list);
    void onProductSuccess(List<SingleProductModel> list);
    void onAddSuccess();
    void onFailed(String msg);

}
