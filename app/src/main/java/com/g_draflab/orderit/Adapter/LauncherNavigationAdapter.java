package com.g_draflab.orderit.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.g_draflab.orderit.Fragments.FirstIntroFragment;

public class LauncherNavigationAdapter extends FragmentPagerAdapter {

    public LauncherNavigationAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 1:
                return new FirstIntroFragment();
            case 2:
                return new FirstIntroFragment();
            case 3:
                return new FirstIntroFragment();
            default:
                return new FirstIntroFragment();

        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
