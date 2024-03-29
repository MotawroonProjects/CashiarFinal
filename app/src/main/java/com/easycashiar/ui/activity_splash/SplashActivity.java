package com.easycashiar.ui.activity_splash;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.animation.LinearInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.easycashiar.R;
import com.easycashiar.databinding.ActivitySplashBinding;
import com.easycashiar.language.Language;
import com.easycashiar.mvp.activity_splash_mvp.SplashPresenter;
import com.easycashiar.mvp.activity_splash_mvp.SplashView;
import com.easycashiar.preferences.Preferences;
import com.easycashiar.tags.Tags;
import com.easycashiar.ui.activity_home.HomeActivity;
import com.easycashiar.ui.activity_login.LoginActivity;
import com.easycashiar.ui.activity_welcome.WelcomeActivity;

import io.paperdb.Paper;

public class SplashActivity extends AppCompatActivity implements SplashView {
    private ActivitySplashBinding binding;
    private SplashPresenter presenter;
    private Preferences preferences;


    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase,Paper.book().read("lang","ar")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Transition transition = new TransitionSet();
            transition.setInterpolator(new LinearInterpolator());
            transition.setDuration(500);
            getWindow().setEnterTransition(transition);
            getWindow().setExitTransition(transition);

        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        initView();
    }

    private void initView() {
        presenter = new SplashPresenter(this,this);
        preferences = Preferences.getInstance();
    }




    @Override
    public void onNavigateToLocationActivity() {
        if(preferences.getSession(this).equals(Tags.session_login)){
            if(preferences.getUserData(this).getIs_confirmed().equals("accepted")){
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();}
            else {
                Intent intent = new Intent(this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        }else {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }}






}