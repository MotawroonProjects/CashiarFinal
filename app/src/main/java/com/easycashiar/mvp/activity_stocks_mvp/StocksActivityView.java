package com.easycashiar.mvp.activity_stocks_mvp;

import com.easycashiar.models.StockModel;

import java.util.List;

public interface StocksActivityView {

    void onSuccess(List<StockModel> list);
    void onDeleteSuccess(int pos);
    void onDeleteFailed(String msg,int pos);
    void onFailed(String msg);
    void onProgressShow();
    void onProgressHide();

}
