package com.cashiar.mvp.stock_balance_activity_mvp;

import com.cashiar.models.SingleProductModel;
import com.cashiar.models.StockModel;

import java.util.List;

public interface StocksBalanceActivityView {

    void onStocksSuccess(List<StockModel> list);
    void onProductSuccess(List<SingleProductModel> list);
    void onAddSuccess();
    void onFailed(String msg);

}
