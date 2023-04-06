package com.easycashiar.models;

import androidx.databinding.BaseObservable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddPremissionsModel extends BaseObservable implements Serializable {

    private List<Integer> ids;


    public AddPremissionsModel() {

        ids = new ArrayList<>();
    }


    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
