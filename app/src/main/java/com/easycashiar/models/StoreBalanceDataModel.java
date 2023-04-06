package com.easycashiar.models;

import java.io.Serializable;
import java.util.List;

public class StoreBalanceDataModel implements Serializable {
    private List<StoreBalanceModel> warehouse_balances;

    public StoreBalanceDataModel(List<StoreBalanceModel> warehouse_balances) {
        this.warehouse_balances = warehouse_balances;
    }

    public List<StoreBalanceModel> getWarehouse_balances() {
        return warehouse_balances;
    }


}
