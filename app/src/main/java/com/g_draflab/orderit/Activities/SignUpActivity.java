package com.g_draflab.orderit.Activities;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.g_draflab.orderit.R;

public class SignUpActivity extends AppCompatActivity {

    Toolbar signUpToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpToolbar = findViewById(R.id.signup_tool_bar);
        signUpToolbar.setTitle("");
        setSupportActionBar(signUpToolbar);
        signUpToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp));
        Drawable backArrow = getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
        backArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(backArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
