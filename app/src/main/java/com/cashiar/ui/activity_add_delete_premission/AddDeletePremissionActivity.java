package com.cashiar.ui.activity_add_delete_premission;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cashiar.R;
import com.cashiar.adapters.PermissionAdapter;
import com.cashiar.adapters.StockPermissionAdapter;
import com.cashiar.databinding.ActivityAddCashierBinding;
import com.cashiar.databinding.ActivityAddDeletePremissionBinding;
import com.cashiar.language.Language;
import com.cashiar.models.AddCashierModel;
import com.cashiar.models.AddPremissionsModel;
import com.cashiar.models.AllPermissionModel;
import com.cashiar.models.SinglePermissionModel;
import com.cashiar.models.StockDataModel;
import com.cashiar.models.StockModel;
import com.cashiar.models.UserModel;
import com.cashiar.mvp.activity_add_cashier_mvp.ActivityAddCashierPresenter;
import com.cashiar.mvp.activity_add_cashier_mvp.AddCashierActivityView;
import com.cashiar.mvp.activity_add_delete_permission_mvp.ActivityAddDeletePremissionPresenter;
import com.cashiar.mvp.activity_add_delete_permission_mvp.AddDeletePremissionActivityView;
import com.cashiar.mvp.activity_add_department_mvp.ActivityAddDepartmentPresenter;
import com.cashiar.preferences.Preferences;
import com.cashiar.share.Common;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class AddDeletePremissionActivity extends AppCompatActivity implements AddDeletePremissionActivityView {
    private ActivityAddDeletePremissionBinding binding;

    private AddPremissionsModel model;

    private ActivityAddDeletePremissionPresenter presenter;
    private Preferences preferences;
    private UserModel userModel;
    private ProgressDialog dialog;
    private List<StockModel> singlePermissionModelList;
    private StockPermissionAdapter stockPermissionAdapter;
    private List<Integer> ids;
    private UserModel body;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_delete_premission);
        initView();

    }

    private void initView() {
        ids = new ArrayList<>();
        singlePermissionModelList = new ArrayList<>();

        stockPermissionAdapter = new StockPermissionAdapter(this, singlePermissionModelList);
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        model = new AddPremissionsModel();
        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setAdapter(stockPermissionAdapter);
        presenter = new ActivityAddDeletePremissionPresenter(this, this);
        presenter.getprofile(userModel);
        presenter.getStockPremission();
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (body!=null&&body.getCurrency() != null && body.getTax_amount() != null) {
                    presenter.checkData(model, userModel);
                } else {
                    Common.CreateDialogAlertProfile(AddDeletePremissionActivity.this,getResources().getString(R.string.please_complete_profile_first));

                }
            }
        });
        binding.llBack.setOnClickListener(view -> {
            finish();
        });


    }

    @Override
    public void onBackPressed() {
        presenter.backPress();
    }

    @Override
    public void onFinished() {
        finish();
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoad() {
        dialog = Common.createProgressDialog(this, getString(R.string.wait));
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onFinishload() {
        dialog.dismiss();
    }


    @Override
    public void onSuccess() {
        finish();
    }

    @Override
    public void onprofileload(UserModel body) {
        this.body = body;
    }

    @Override
    public void onProgressShow() {
        binding.progBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProgressHide() {
        binding.progBar.setVisibility(View.GONE);

    }

    @Override
    public void onpermisionSuccess(StockDataModel body) {

        singlePermissionModelList.clear();
        singlePermissionModelList.addAll(body.getData());
        stockPermissionAdapter.notifyDataSetChanged();
    }

    public void addid(int id) {
        Log.e("id", id + "");
        ids.add(id);
        model.setIds(ids);
        binding.setModel(model);
    }

    public void removeid(int id) {
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i) == id) {
                ids.remove(i);
                break;
            }
        }
        model.setIds(ids);
        binding.setModel(model);
    }
}