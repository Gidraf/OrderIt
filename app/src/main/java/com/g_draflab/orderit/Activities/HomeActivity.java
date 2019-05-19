package com.g_draflab.orderit.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.g_draflab.orderit.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        signInButton = findViewById(R.id.sign_in_home_button);
        signInButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==signInButton){
            startActivity(new Intent(this, SignInActivity.class));
        }
    }
}
