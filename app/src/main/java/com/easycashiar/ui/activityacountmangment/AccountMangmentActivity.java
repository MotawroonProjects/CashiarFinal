package com.easycashiar.ui.activityacountmangment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


import com.easycashiar.R;
import com.easycashiar.adapters.ViewPagerAdapter;
import com.easycashiar.databinding.ActivityAccountManagmentBinding;
import com.easycashiar.language.Language;
import com.easycashiar.ui.activityacountmangment.fragments.FragmentReports;
import com.easycashiar.ui.activityacountmangment.fragments.aggerate.FragmentAggregateData;
import com.easycashiar.ui.activityacountmangment.fragments.overview.FragmentOverView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class AccountMangmentActivity extends AppCompatActivity {
    private ActivityAccountManagmentBinding binding;
    private String lang;
    private List<Fragment> fragmentList;
    private List<String> titles;
    private ViewPagerAdapter adapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase, Paper.book().read("lang", Locale.getDefault().getLanguage())));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_account_managment);
        initView();

    }

    private void initView() {

        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(lang);
        fragmentList = new ArrayList<>();
        titles = new ArrayList<>();
        binding.tab.setupWithViewPager(binding.pager);
        addFragments_Titles();
        binding.pager.setOffscreenPageLimit(fragmentList.size());

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragments(fragmentList);
        adapter.addTitles(titles);
        binding.pager.setAdapter(adapter);
        binding.llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void addFragments_Titles() {
        fragmentList.add(FragmentReports.newInstance());

        fragmentList.add(FragmentAggregateData.newInstance());
        fragmentList.add(FragmentOverView.newInstance());

        titles.add(getString(R.string.reports));

        titles.add(getString(R.string.aggregate_data));
        titles.add(getString(R.string.overview));


    }


}
