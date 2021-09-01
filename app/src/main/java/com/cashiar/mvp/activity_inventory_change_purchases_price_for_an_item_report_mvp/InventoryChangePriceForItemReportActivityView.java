package com.cashiar.mvp.activity_inventory_change_purchases_price_for_an_item_report_mvp;

import com.cashiar.models.AllSalesPurshReportModel;

public interface InventoryChangePriceForItemReportActivityView {
    void onFinished();
    void onProgressShow();
    void onProgressHide();
    void onFailed(String msg);
    void onSuccess(AllSalesPurshReportModel allSalesPurshReportModel);
    void onDateSelected(String date,int i);

}
