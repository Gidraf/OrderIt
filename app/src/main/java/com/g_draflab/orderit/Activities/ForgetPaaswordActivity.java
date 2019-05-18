package com.g_draflab.orderit.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.g_draflab.orderit.Models.CustomerRegisterResponse;
import com.g_draflab.orderit.R;
import com.g_draflab.orderit.Retrofit.ApiUtils;
import com.g_draflab.orderit.Retrofit.AuthServices;
import com.rengwuxian.materialedittext.MaterialEditText;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.g_draflab.orderit.Activities.SignUpActivity.isValidEmail;

public class ForgetPaaswordActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar forgetToolbar;
    MaterialEditText emailedit;
    AuthServices authServices;
    Button forgetPasswordButton;
    SpotsDialog.Builder dialog;
    AlertDialog builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_paasword);
        forgetToolbar = findViewById(R.id.reset_password_tool_bar);
        forgetToolbar.setTitle("");
        setSupportActionBar(forgetToolbar);
        authServices = ApiUtils.registerCustomerService();
        forgetToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp));
        Drawable backArrow = getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
        backArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(backArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        dialog = new SpotsDialog.Builder().setContext(this);
        dialog.setTheme(R.style.sports_alert_dialog);
        emailedit = findViewById(R.id.reset_password_email_edit_Text);
        forgetPasswordButton = findViewById(R.id.reset_password_button);

        forgetPasswordButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v==forgetPasswordButton){
            sendForgetPasswordRequest();
        }
    }

        private void sendForgetPasswordRequest() {

        String email = emailedit.getText().toString();

        if(email.isEmpty()){
            emailedit.setFloatingLabelText(getResources().getString(R.string.empty_username));
            emailedit.setFloatingLabelTextColor(Color.RED);
            return;
        }

        if(!isValidEmail(email) ){
            emailedit.setFloatingLabelText(getResources().getString(R.string.invalid_email));
            emailedit.setFloatingLabelTextColor(Color.RED);
            return;
        }

        showProgressBar();
        authServices.forgetpassword(email).enqueue(new Callback<CustomerRegisterResponse>() {
            @Override
            public void onResponse(Call<CustomerRegisterResponse> call, Response<CustomerRegisterResponse> response) {
                if(response.isSuccessful()) {
                    dismissProgrssBar();
                    finish();
                    return;
                }
                dismissProgrssBar();
                Toast.makeText(ForgetPaaswordActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CustomerRegisterResponse> call, Throwable t) {
                dismissProgrssBar();
                Toast.makeText(ForgetPaaswordActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showProgressBar(){
        dialog.setMessage(getResources().getString(R.string.registering));
        dialog.setCancelable(false);
        builder = dialog.build();
        builder.show();
    }

    public void dismissProgrssBar(){
        if(builder.isShowing()){
            builder.dismiss();
        }
    }

}
