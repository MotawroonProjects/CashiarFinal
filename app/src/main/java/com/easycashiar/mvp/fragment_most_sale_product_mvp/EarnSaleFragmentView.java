package com.easycashiar.mvp.fragment_most_sale_product_mvp;

import com.easycashiar.models.AllProductsModel;
import com.easycashiar.models.SettingModel;

public interface EarnSaleFragmentView {
    void onFailed(String msg);

    void onLoad();

    void onFinishload();




    void onpurchase(SettingModel body);

    void mostsale(AllProductsModel body);
}
