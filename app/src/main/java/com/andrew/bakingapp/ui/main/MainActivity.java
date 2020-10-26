package com.andrew.bakingapp.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.andrew.bakingapp.R;
import com.andrew.bakingapp.adapter.TabAdapter;
import com.andrew.bakingapp.utils.Constant;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVar();
    }

    private void initVar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setElevation(0f);
        }

        tabLayout = findViewById(R.id.tl_main);
        viewPager = findViewById(R.id.vp_main);

        setTabLayout();
    }

    private void setTabLayout() {
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), Constant.TAB_NAME.length);

        for (String s : Constant.TAB_NAME) {
            tabLayout.addTab(tabLayout.newTab().setText(s));
        }

        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        viewPager.setAdapter(tabAdapter);
        viewPager.setOffscreenPageLimit(1);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                for (int i = 0; i < Constant.TAB_NAME.length; i++) {
                    if(tab.getText() != null && tab.getText().equals(Constant.TAB_NAME[i])){
                        viewPager.setCurrentItem(i);
                        break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}