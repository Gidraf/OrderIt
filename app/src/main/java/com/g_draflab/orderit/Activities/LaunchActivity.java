package com.g_draflab.orderit.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.FacebookSdk;
import com.g_draflab.orderit.Activities.NavigationActivity;
import com.g_draflab.orderit.R;

import io.paperdb.Paper;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Paper.init(this);
        String token = Paper.book().read(getString(R.string.token));
        String hasSigned_in_before = Paper.book().read(getString(R.string.has_signed_in_before));
        if(token == null && !Boolean.parseBoolean(hasSigned_in_before)){
            startActivity(new Intent(this, NavigationActivity.class));
            finish(); return;
        }
        else if (token == null && Boolean.parseBoolean(hasSigned_in_before)){
            startActivity(new Intent(this,SignInActivity.class));
            finish();
            return;
        }
        startActivity(new Intent(LaunchActivity.this, MainActivity.class));
        finish();
    }
}
