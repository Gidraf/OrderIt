package com.g_draflab.orderit.Activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.RelativeLayout;;

import com.g_draflab.orderit.Adapter.LauncherNavigationAdapter;
import com.g_draflab.orderit.R;

public class NavigationActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager navigationPager;
    LauncherNavigationAdapter navigationAdapter;
    RelativeLayout continueButtonContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        navigationPager = findViewById(R.id.intro_navigation);
        navigationAdapter =  new LauncherNavigationAdapter(getSupportFragmentManager());
        navigationPager.setAdapter(navigationAdapter);
        continueButtonContainer = findViewById(R.id.continue_container);
        continueButtonContainer.setOnClickListener(this);
        TabLayout tabLayout = findViewById(R.id.navigation_indicator);
        tabLayout.setupWithViewPager(navigationPager, true);


    }

    @Override
    public void onClick(View v) {

        if (v == continueButtonContainer){
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }
}
