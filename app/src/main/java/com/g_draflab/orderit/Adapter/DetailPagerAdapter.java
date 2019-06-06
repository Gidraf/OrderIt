package com.g_draflab.orderit.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.g_draflab.orderit.Fragments.DetailsFragment;

public class DetailPagerAdapter extends FragmentPagerAdapter {
    public DetailPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return new DetailsFragment();
    }

    @Override
    public int getCount() {
        return 5;
    }
}
