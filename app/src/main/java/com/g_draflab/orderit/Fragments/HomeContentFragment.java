package com.g_draflab.orderit.Fragments;

import android.app.AlertDialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.g_draflab.orderit.Adapter.BottomNavigationAdapter;
import com.g_draflab.orderit.Interfaces.OnNavigationClickedListener;
import com.g_draflab.orderit.Models.Department;
import com.g_draflab.orderit.R;
import com.g_draflab.orderit.Retrofit.ApiUtils;
import com.g_draflab.orderit.Retrofit.DepartmentsServices;

import net.skoumal.fragmentback.BackFragmentAppCompatActivity;
import net.skoumal.fragmentback.BackFragmentFragmentActivity;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeContentFragment extends Fragment {

    OnNavigationClickedListener clickedListener;
    ViewPager contentViewpager;
    BottomNavigationAdapter navigationAdapter;
    TabLayout bottomNavigationTablayout;
    RelativeLayout parentView;
    public HomeContentFragment() {

    }

    public void setClickedListener(OnNavigationClickedListener listener){
        this.clickedListener =  listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home_content, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        toolbar.setTitle("");
        bottomNavigationTablayout = view.findViewById(R.id.bottom_navigation_tab_layout);
        parentView = view.findViewById(R.id.parent_view);
        Drawable backArrow = getResources().getDrawable(R.drawable.ic_dehaze_black_24dp);
        backArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        ImageView homeImage = new ImageView(getContext());
        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(50, 50);
        homeImage.setLayoutParams(params);
        homeImage.setImageResource(R.drawable.ic_dehaze_black_24dp);
        toolbar.addView(homeImage);
        homeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickedListener !=null) {
                    clickedListener.isNavigationDrawerClicked(true);
                }
            }
        });
        navigationAdapter = new BottomNavigationAdapter(getActivity().getSupportFragmentManager(), getContext());
        contentViewpager = view.findViewById(R.id.content_view_pager);
        contentViewpager.setAdapter(navigationAdapter);
        bottomNavigationTablayout.setupWithViewPager(contentViewpager);
        for (int i = 0; i < bottomNavigationTablayout.getTabCount(); i++) {
            TabLayout.Tab tab = bottomNavigationTablayout.getTabAt(i);
            View tabView = navigationAdapter.getTabView(i);
            tab.setCustomView(tabView);
        }
        return view;
    }


}
