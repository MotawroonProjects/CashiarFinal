package com.easycashiar.mvp.activity_product_profit_rate_mvp;

import com.easycashiar.models.AllSalesPurshReportModel;

public interface ProductProfitRateReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllSalesPurshReportModel allSalesPurshReportModel);
    void onDateSelected(String date,int i);

}
