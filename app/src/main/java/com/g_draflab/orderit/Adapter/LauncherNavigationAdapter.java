package com.g_draflab.orderit.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.g_draflab.orderit.Fragments.FirstFragment;

public class LauncherNavigationAdapter extends FragmentPagerAdapter {

    public LauncherNavigationAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 1:
                return new FirstFragment();
            case 2:
                return new FirstFragment();
            case 3:
                return new FirstFragment();
            default:
                return new FirstFragment();

        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
