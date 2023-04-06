package com.easycashiar.models;

import java.io.Serializable;
import java.util.List;

public class AllExpensesModel implements Serializable {
    private List<SingleExpensesModel> data;
    private int status;

    public List<SingleExpensesModel> getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }
}
