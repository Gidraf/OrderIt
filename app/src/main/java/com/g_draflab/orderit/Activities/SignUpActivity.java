package com.g_draflab.orderit.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.g_draflab.orderit.Models.CustomerRegisterResponse;
import com.g_draflab.orderit.R;
import com.g_draflab.orderit.Retrofit.ApiUtils;
import com.g_draflab.orderit.Retrofit.AuthServices;
import com.rengwuxian.materialedittext.MaterialEditText;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar signUpToolbar;
    AuthServices registerCustomerService;
    MaterialEditText emailedit, firstNameedit, lastNameedit, passwordedit, retypePasswordedit, dateedit, monthedit, yearedit;
    Button signupButton;
    Switch termsSwitch;
    ImageView genderSelector;
    TextView maleGender, femaleGender, signInTextView;
    SpotsDialog.Builder dialog;
    AlertDialog builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }

    /***
     * initialize class variables
     * @return void
     * ***/

    private void init() {
        signUpToolbar = findViewById(R.id.signup_tool_bar);
        signUpToolbar.setTitle("");
        registerCustomerService = ApiUtils.registerCustomerService();
        emailedit = findViewById(R.id.sign_up_email_edit_Text);
        firstNameedit = findViewById(R.id.first_name_edit_Text);
        lastNameedit = findViewById(R.id.last_name_edit_text);
        passwordedit = findViewById(R.id.sign_up_password_edit_text);
        signInTextView = findViewById(R.id.sign_Up_sign_in_button);
        retypePasswordedit = findViewById(R.id.sign_up_retype_password_edit_text);
        monthedit = findViewById(R.id.month_of_birth);
        dateedit = findViewById(R.id.date_of_birth);
        yearedit = findViewById(R.id.year_of_birth);
        termsSwitch = findViewById(R.id.terms_switch);
        signupButton = findViewById(R.id.sign_up_button);
        genderSelector = findViewById(R.id.gender_selector_image);
        femaleGender = findViewById(R.id.female_selector);
        maleGender = findViewById(R.id.male_selector);
        signupButton.setOnClickListener(this);
        femaleGender.setOnClickListener(this);
        signInTextView.setOnClickListener(this);
        femaleGender.setAlpha(0.8f);
        maleGender.setOnClickListener(this);
        dialog = new SpotsDialog.Builder().setContext(this);
        setSupportActionBar(signUpToolbar);
        dialog.setTheme(R.style.sports_alert_dialog);
        signUpToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp));
        Drawable backArrow = getResources().getDrawable(R.drawable.ic_keyboard_arrow_left_black_24dp);
        backArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(backArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        signupButton.setAlpha(0.8f);
        signupButton.setEnabled(false);
        termsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    signupButton.setAlpha(0.8f);
                    signupButton.setEnabled(isChecked);
                    return;
                }
                signupButton.setAlpha(1f);
                signupButton.setEnabled(isChecked);
            }
        });
        firstNameedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                firstNameedit.setFloatingLabelText(getResources().getString(R.string.first_name));
                firstNameedit.setFloatingLabelTextColor(R.color.colorPrimary);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lastNameedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lastNameedit.setFloatingLabelText(getResources().getString(R.string.last_name));
                lastNameedit.setFloatingLabelTextColor(R.color.colorPrimary);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        emailedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                emailedit.setFloatingLabelText(getResources().getString(R.string.email));
                emailedit.setFloatingLabelTextColor(R.color.colorPrimary);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordedit.setFloatingLabelText(getResources().getString(R.string.password));
                passwordedit.setFloatingLabelTextColor(R.color.colorPrimary);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        retypePasswordedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                retypePasswordedit.setFloatingLabelText(getResources().getString(R.string.retype_password));
                retypePasswordedit.setFloatingLabelTextColor(R.color.colorPrimary);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dateedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dateedit.setFloatingLabelText(getResources().getString(R.string.dd));
                dateedit.setFloatingLabelTextColor(R.color.colorPrimary);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        monthedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                monthedit.setFloatingLabelText(getResources().getString(R.string.mm));
                monthedit.setFloatingLabelTextColor(R.color.colorPrimary);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        yearedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                yearedit.setFloatingLabelText(getResources().getString(R.string.yyyy));
                yearedit.setFloatingLabelTextColor(R.color.colorPrimary);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    public void onClick(View v) {
        if(v==signupButton){
            registerCustomer();
        }
        else if(v==maleGender){
            femaleGender.setAlpha(1f);
            maleGender.setAlpha(0.8f);
            genderSelector.setImageDrawable((getResources().getDrawable(R.drawable.ic_male_select_indicator)));
        }else if(v==femaleGender){
            genderSelector.setImageDrawable((getResources().getDrawable(R.drawable.ic_female_selector)));
            femaleGender.setAlpha(0.8f);
            maleGender.setAlpha(1f);
        }
        else if (v==signInTextView){
            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            finish();
        }
    }

    /**
     * Register Customer
     *
     * @return void
     * ***/

    private void registerCustomer() {

        String firstname = firstNameedit.getText().toString();
        String lastName = lastNameedit.getText().toString();
        String month = monthedit.getText().toString();
        String date = dateedit.getText().toString();
        String year = yearedit.getText().toString();
        String name = firstNameedit.getText().toString() + " " + lastNameedit.getText().toString();
        String email = emailedit.getText().toString();
        String password = passwordedit.getText().toString();
        String password_retyped = retypePasswordedit.getText().toString();

        if(firstname.isEmpty()){
            firstNameedit.setFloatingLabelText(getResources().getString(R.string.empty_username));
            firstNameedit.setFloatingLabelTextColor(Color.RED);
            return;
        }

        if(lastName.isEmpty()){
            lastNameedit.setFloatingLabelText(getResources().getString(R.string.empty_username));
            lastNameedit.setFloatingLabelTextColor(Color.RED);
            return;
        }

        if(email.isEmpty()){
            emailedit.setFloatingLabelText(getResources().getString(R.string.empty_username));
            emailedit.setFloatingLabelTextColor(Color.RED);
            return;
        }

        if(!isValidEmail(email)){
            emailedit.setFloatingLabelText(getResources().getString(R.string.invalid_email));
            emailedit.setFloatingLabelTextColor(Color.RED);
            return;
        }

        if(password.isEmpty()){
            passwordedit.setFloatingLabelText(getResources().getString(R.string.empty_username));
            passwordedit.setFloatingLabelTextColor(Color.RED);
            return;
        }

        if(password_retyped.isEmpty()){
            retypePasswordedit.setFloatingLabelText(getResources().getString(R.string.empty_username));
            retypePasswordedit.setFloatingLabelTextColor(Color.RED);
            return;
        }

        if(!password.equals(password_retyped)){
            passwordedit.setFloatingLabelText(getResources().getString(R.string.match_password));
            passwordedit.setFloatingLabelTextColor(Color.RED);
            retypePasswordedit.setFloatingLabelText(getResources().getString(R.string.match_password));
            retypePasswordedit.setFloatingLabelTextColor(Color.RED);
            return;
        }

        if(date.isEmpty()){
            dateedit.setFloatingLabelTextColor(Color.RED);
            return;
        }

        if(month.isEmpty()){
            monthedit.setFloatingLabelTextColor(Color.RED);
            return;
        }

        if(year.isEmpty()){
            yearedit.setFloatingLabelTextColor(Color.RED);
            return;
        }

        showProgressBar();
        registerCustomerService.registerCustomer(name, email,password).enqueue(new Callback<CustomerRegisterResponse>() {
            @Override
            public void onResponse(Call<CustomerRegisterResponse> call, Response<CustomerRegisterResponse> response) {
                if(response.isSuccessful()) {
                    dismissProgrssBar();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(SignUpActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    sweetAlertDialog.setCancelable(false);
                    sweetAlertDialog.setContentText(getResources().getString(R.string.success_registration));
                    sweetAlertDialog.setConfirmText(getResources().getString(R.string.sign_in)).setConfirmClickListener(
                            new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                                    finish();
                                }
                            }
                    ).show();
                    return;
                }
                dismissProgrssBar();
                Toast.makeText(SignUpActivity.this, "An error occured", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CustomerRegisterResponse> call, Throwable t) {
                dismissProgrssBar();
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
