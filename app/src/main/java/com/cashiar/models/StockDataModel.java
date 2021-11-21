package com.cashiar.models;

import java.io.Serializable;
import java.util.List;

public class StockDataModel implements Serializable {
private List<StockModel> data;

    public List<StockModel> getData() {
        return data;
    }
}
