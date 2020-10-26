package com.andrew.bakingapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.andrew.bakingapp.ui.main.fragment.MainFragment;

public class TabAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;

    public TabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numOfTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
