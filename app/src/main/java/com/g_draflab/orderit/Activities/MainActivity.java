package com.g_draflab.orderit.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.g_draflab.orderit.Adapter.BottomNavigationAdapter;
import com.g_draflab.orderit.Fragments.HomeContentFragment;
import com.g_draflab.orderit.Fragments.HomeContentHolderFragment;
import com.g_draflab.orderit.Fragments.NavigationDrawerFragment;
import com.g_draflab.orderit.Interfaces.OnBackPresspressed;
import com.g_draflab.orderit.Interfaces.OnNavigationClickedListener;
import com.g_draflab.orderit.R;

import net.skoumal.fragmentback.BackFragmentFragmentActivity;

import java.util.List;

public class MainActivity extends BackFragmentFragmentActivity
        implements SlidingPaneLayout.PanelSlideListener, OnNavigationClickedListener, OnBackPresspressed {
    HomeContentFragment navigationDrawerFragment;
    SlidingPaneLayout drawer;
    RelativeLayout season;
    LinearLayout natureView, regionalview, promotionalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<Fragment> availableFragements = getSupportFragmentManager().getFragments();

        for(Fragment f: availableFragements){
            if(!(f instanceof HomeContentHolderFragment)){
                Toast.makeText(this, "hey", Toast.LENGTH_SHORT).show();
                season =   ((HomeContentHolderFragment) f).offerView;
              natureView =  ((HomeContentHolderFragment) f).womenWearView;
              regionalview =  ((HomeContentHolderFragment) f).menWearView;
              promotionalView =  ((HomeContentHolderFragment) f).promotextView;
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);
        drawer.setPanelSlideListener(this);
        navigationDrawerFragment = (HomeContentFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        if(navigationDrawerFragment != null){
        navigationDrawerFragment.setClickedListener(this);
        }
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onPanelSlide(@NonNull View view, float v) {

    }

    @Override
    public void onPanelOpened(@NonNull View view) {
        isNavigationDrawerClicked(false);
    }

    @Override
    public void onPanelClosed(@NonNull View view) {
    }

    @Override
    public void isNavigationDrawerClicked(boolean clicked) {
        if(clicked) {
            drawer.openPane();
        }
    }

    @Override
    public void backpessed() {

    }
}
