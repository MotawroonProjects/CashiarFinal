package com.easycashiar.ui.activity_profile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.easycashiar.R;
import com.easycashiar.databinding.ActivityExpensesBinding;
import com.easycashiar.databinding.ActivityProfileBinding;
import com.easycashiar.language.Language;
import com.easycashiar.models.UserModel;
import com.easycashiar.mvp.activity_profile_mvp.ActivityProfilePresenter;
import com.easycashiar.mvp.activity_profile_mvp.ProfileActivityView;
import com.easycashiar.preferences.Preferences;
import com.easycashiar.share.Common;
import com.easycashiar.ui.activity_edit_cashier_profile.EditprofileCashierActivity;
import com.easycashiar.ui.activity_edit_profile.EditprofileActivity;
import com.easycashiar.ui.activity_subscription.SubscriptionActivity;

import io.paperdb.Paper;

public class ProfileActivity extends AppCompatActivity implements ProfileActivityView {
    private ActivityProfileBinding binding;
    private ActivityProfilePresenter presenter;
    private String lang;

    private Preferences preferences;
    private UserModel userModel;
    private ProgressDialog dialog2;
    private int type = -1;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", "ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        initView();
    }


    private void initView() {
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        binding.setUserModel(userModel);

        Paper.init(this);
        lang = Paper.book().read("lang", "ar");
        binding.setLang(lang);
        presenter = new ActivityProfilePresenter(this, this);
        presenter.getprofile(userModel);

        binding.llBack.setOnClickListener(view -> {
            finish();
        });
        binding.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.update();
            }
        });
        binding.btnsubscrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.subscripe();
            }
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
    public void onLoad() {
        if (dialog2 == null) {
            dialog2 = Common.createProgressDialog(this, getString(R.string.wait));
            dialog2.setCancelable(false);
        } else {
            dialog2.dismiss();
        }
        dialog2.show();
    }

    @Override
    public void onFinishload() {
        dialog2.dismiss();
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onprofileload(UserModel body) {
        if (userModel.getId() != body.getTrader_id()) {
            type = 1;
        } else {
            type = 0;
        }
    }

    @Override
    public void subscriptions() {
        Intent intent = new Intent(ProfileActivity.this, SubscriptionActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void update() {
        if (type == 0) {
            Intent intent = new Intent(ProfileActivity.this, EditprofileActivity.class);
            startActivityForResult(intent, 100);
        } else {
            Intent intent = new Intent(ProfileActivity.this, EditprofileCashierActivity.class);
            startActivityForResult(intent, 100);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (preferences != null) {
                userModel = preferences.getUserData(this);
                binding.setUserModel(userModel);
            }
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.getprofile(userModel);
    }
}