package com.cashiar.mvp.activity_stocks_mvp;

import com.cashiar.models.AllCategoryModel;
import com.cashiar.models.Slider_Model;
import com.cashiar.models.StockModel;

import java.util.List;

public interface StocksActivityView {

    void onSuccess(List<StockModel> list);
    void onFailed(String msg);
    void onProgressShow();
    void onProgressHide();

}
