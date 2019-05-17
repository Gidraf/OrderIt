package com.g_draflab.orderit.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.g_draflab.orderit.R;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialEditText passwordEditText, emailEditText;
    TextView forgetPasswordButton, signUpButton;
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        passwordEditText = findViewById(R.id.password_edit_text);
        emailEditText = findViewById(R.id.email_edit_Text);
        forgetPasswordButton = findViewById(R.id.forget_password_btn);
        signInButton = findViewById(R.id.sign_in_button);
        signUpButton = findViewById(R.id.sign_Up_sign_button);

        forgetPasswordButton.setOnClickListener(this);
        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==forgetPasswordButton){
            startActivity(new Intent(this, ForgetPaaswordActivity.class));
        }
        else if (v == signUpButton){
            startActivity(new Intent(this, SignUpActivity.class));
        }
    }
}
