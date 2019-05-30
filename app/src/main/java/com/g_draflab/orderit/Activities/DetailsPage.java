package com.g_draflab.orderit.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.g_draflab.orderit.Adapter.DetailPagerAdapter;
import com.g_draflab.orderit.R;

public class DetailsPage extends AppCompatActivity {
    DetailPagerAdapter adapter;
    ViewPager detailpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);
        adapter =new DetailPagerAdapter(getSupportFragmentManager());
        detailpager = findViewById(R.id.detailViewpage);
        detailpager.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
