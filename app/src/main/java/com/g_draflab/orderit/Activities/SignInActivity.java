package com.g_draflab.orderit.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.g_draflab.orderit.Models.CustomerRegisterResponse;
import com.g_draflab.orderit.R;
import com.g_draflab.orderit.Retrofit.ApiUtils;
import com.g_draflab.orderit.Retrofit.AuthServices;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Arrays;
import java.util.Collections;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.appevents.UserDataStore.EMAIL;
import static com.g_draflab.orderit.Activities.SignUpActivity.isValidEmail;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialEditText passwordEditText, emailEditText;
    TextView forgetPasswordButton, signUpButton;
    Button signInButton;
    Toolbar signToolbar;
    AuthServices authServices;
    SpotsDialog.Builder dialog;
    AlertDialog builder;
    ImageView facebookImage;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        init();
        Paper.init(this);
        callbackManager = CallbackManager.Factory.create();

        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };

        // If the access token is available already assign it.
        final AccessToken accessToken = AccessToken.getCurrentAccessToken();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        showProgressBar();
                        authServices.facebookloginCustomer(accessToken != null ? accessToken.getToken():"").enqueue(new Callback<CustomerRegisterResponse>() {
                            @Override
                            public void onResponse(Call<CustomerRegisterResponse> call, Response<CustomerRegisterResponse> response) {
                                if(response.isSuccessful()){
                                    storeToken(getString(R.string.token), response.body().getAccessToken());
                                    dismissProgrssBar();
                                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                                    finish();
                                }

                                dismissProgrssBar();
                                Toast.makeText(SignInActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<CustomerRegisterResponse> call, Throwable t) {
                                dismissProgrssBar();
                                Toast.makeText(SignInActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(SignInActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {

                        Toast.makeText(SignInActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    /***
     * initialize variables
     *
     * @return void
     * ***/

    private void init() {
        signToolbar = findViewById(R.id.signin_tool_bar);
        signToolbar.setTitle("");
        setSupportActionBar(signToolbar);
        signToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp));
        Drawable backArrow = getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
        backArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(backArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        facebookImage = findViewById(R.id.facebook_login_button);
        facebookImage.setOnClickListener(this);
        dialog = new SpotsDialog.Builder().setContext(this);
        dialog.setTheme(R.style.sports_alert_dialog);
        authServices = ApiUtils.registerCustomerService();
        passwordEditText = findViewById(R.id.password_edit_text);
        emailEditText = findViewById(R.id.email_edit_Text);
        forgetPasswordButton = findViewById(R.id.forget_password_btn);
        signInButton = findViewById(R.id.sign_in_button);
        signUpButton = findViewById(R.id.sign_Up_sign_button);

        forgetPasswordButton.setOnClickListener(this);
        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);

        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                emailEditText.setFloatingLabelText(getResources().getString(R.string.email));
                emailEditText.setFloatingLabelTextColor(R.color.colorPrimary);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordEditText.setFloatingLabelText(getResources().getString(R.string.password));
                passwordEditText.setFloatingLabelTextColor(R.color.colorPrimary);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==forgetPasswordButton){
            startActivity(new Intent(this, ForgetPaaswordActivity.class));
        }
        else if (v == signUpButton){
            startActivity(new Intent(this, SignUpActivity.class));
        }
        else if(v==signInButton){
            loginCustomer();
        }
        else if (v==facebookImage){
            LoginManager.getInstance().logInWithReadPermissions(SignInActivity.this, Collections.singletonList("email"));
        }
    }

    /***
     * Login user
     *
     * @return void
     * **/

    private void loginCustomer() {


        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();



        if(email.isEmpty()){
            emailEditText.setFloatingLabelText(getResources().getString(R.string.empty_username));
            emailEditText.setFloatingLabelTextColor(Color.RED);
            return;
        }

        if(!isValidEmail(email) ){
            emailEditText.setFloatingLabelText(getResources().getString(R.string.invalid_email));
            emailEditText.setFloatingLabelTextColor(Color.RED);
            return;
        }

        if(password.isEmpty()){
            passwordEditText.setFloatingLabelText(getResources().getString(R.string.empty_username));
            passwordEditText.setFloatingLabelTextColor(Color.RED);
            return;
        }

        showProgressBar();
        authServices.loginCustomer(email,password).enqueue(new Callback<CustomerRegisterResponse>() {
            @Override
            public void onResponse(Call<CustomerRegisterResponse> call, Response<CustomerRegisterResponse> response) {
                if(response.isSuccessful()) {
                    dismissProgrssBar();
                    storeToken(getString(R.string.token), response.body().getAccessToken());
                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    finish();
                    return;
                }
                dismissProgrssBar();
                Toast.makeText(SignInActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CustomerRegisterResponse> call, Throwable t) {
                dismissProgrssBar();
                Toast.makeText(SignInActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /***
     * Stores token to sharedPreferences
     *
     * @param {key}
     * @param {token}
     *
     * ***/

    public void storeToken(String key, String data){
        Paper.book().write(key, data);
    }

    public void showProgressBar(){
        dialog.setMessage(getResources().getString(R.string.signingin));
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
