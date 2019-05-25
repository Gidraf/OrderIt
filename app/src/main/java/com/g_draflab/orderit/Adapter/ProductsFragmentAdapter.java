package com.g_draflab.orderit.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.g_draflab.orderit.Fragments.HomeContentFragment;
import com.g_draflab.orderit.Fragments.NavigationDrawerFragment;
import com.g_draflab.orderit.Fragments.ProductsFragment;
import com.g_draflab.orderit.Models.Category;
import com.g_draflab.orderit.R;

import java.util.List;

public class ProductsFragmentAdapter extends FragmentPagerAdapter {

    List<Category> categories;
    Context context;

    public ProductsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public ProductsFragmentAdapter(FragmentManager fm, List<Category> categories, Context context) {
        super(fm);
        this.categories = categories;
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        Bundle bundle= new Bundle();
        bundle.putInt("categoryId", Integer.parseInt(categories.get(i).getCategoryId()));
        ProductsFragment fragment = new ProductsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return   categories == null ? 0 :categories.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getName();
    }
}
