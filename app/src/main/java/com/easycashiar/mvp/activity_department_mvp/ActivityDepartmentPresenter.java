package com.easycashiar.mvp.activity_department_mvp;

import android.content.Context;

import com.easycashiar.models.UserModel;
import com.easycashiar.preferences.Preferences;

public class ActivityDepartmentPresenter {
    private UserModel userModel;
    private Preferences preferences;
    private DepartmentActivityView view;
    private Context context;

    public ActivityDepartmentPresenter(DepartmentActivityView view, Context context) {
        this.view = view;
        this.context = context;
    }
    public void backPress() {

            view.onFinished();


    }
    public void addproducts() {
        view.onAddproducts();
    }
    public void adddepartments() {
        view.onAdddepartments();
    }
    public void adddiscounts() {
        view.onAdddiscounts();
    }

}
