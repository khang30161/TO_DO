package com.example.khang.myapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.khang.myapp.fragment.CompleteActivity;
import com.example.khang.myapp.fragment.Doing;

public class PageAdapterFrament extends FragmentStatePagerAdapter {

    int mNumOfTabs = 2;


    public PageAdapterFrament(FragmentManager manager) {
        super(manager);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Doing tab1 = new Doing();
                return tab1;
            case 1:
                CompleteActivity tab2 = new CompleteActivity();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Doing";
            case 1:
                return "Complete";
        }
        return null;
    }
}