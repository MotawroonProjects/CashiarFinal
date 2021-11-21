package com.cashiar.models;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.cashiar.BR;
import com.cashiar.R;

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
