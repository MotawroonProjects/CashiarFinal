package com.cashiar.models;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.cashiar.BR;
import com.cashiar.R;


public class AddStockModel extends BaseObservable {
    private String id;
    private String name="";

    public ObservableField<String> error_name = new ObservableField<>();


    public boolean isDataValid(Context context) {
        if (!name.trim().isEmpty() )
        {

            error_name.set(null);

            return true;
        } else {

            error_name.set(context.getString(R.string.field_required));


            return false;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }


}
