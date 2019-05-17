package com.g_draflab.orderit.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.g_draflab.orderit.Activities.NavigationActivity;
import com.g_draflab.orderit.R;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        startActivity(new Intent(this, NavigationActivity.class));
        finish();
    }
}
