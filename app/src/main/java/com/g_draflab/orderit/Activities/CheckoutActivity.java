package com.g_draflab.orderit.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.g_draflab.orderit.Fragments.AddressFragment;
import com.g_draflab.orderit.Fragments.PaymentFragment;
import com.g_draflab.orderit.Interfaces.OnRequestSuccessListener;
import com.g_draflab.orderit.Models.Customer;
import com.g_draflab.orderit.Models.CustomerRegisterResponse;
import com.g_draflab.orderit.R;
import com.g_draflab.orderit.Retrofit.ApiUtils;
import com.g_draflab.orderit.Retrofit.AuthServices;
import com.rengwuxian.materialedittext.MaterialEditText;

import net.skoumal.fragmentback.BackFragment;
import net.skoumal.fragmentback.BackFragmentFragmentActivity;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity implements OnRequestSuccessListener {
    TextView address, payment, complete;
    ImageView addresspolygon, paymentpolygon, completePolygon;
    ViewPager checkoutPager;
    View fragmentAddressView, fragmentPaymentView, fragmentCompleteView;
    int shortAnimationDuration;
    AuthServices authServices;
    Customer customer;
    PaymentFragment paymentFragment;
    AddressFragment addressFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        address = findViewById(R.id.address_tab_text_view);
        payment = findViewById(R.id.payment_tab_text_view);
        complete = findViewById(R.id.complete_tab_text_view);
        addresspolygon = findViewById(R.id.address_polygon);
        paymentpolygon = findViewById(R.id.payment_polygon);
        completePolygon = findViewById(R.id.complete_polygon);
        fragmentCompleteView = findViewById(R.id.complete_fragment);
        fragmentAddressView = findViewById(R.id.address_fragment);
        fragmentAddressView.setVisibility(View.GONE);
        fragmentPaymentView = findViewById(R.id.payment_fragment);
        fragmentPaymentView.setVisibility(View.GONE);
        fragmentCompleteView.setVisibility(View.GONE);
        Paper.init(this);
        addressFragment = (AddressFragment) getSupportFragmentManager().findFragmentById(R.id.address_fragment);
        paymentFragment = (PaymentFragment) getSupportFragmentManager().findFragmentById(R.id.payment_fragment);
        addressFragment.setRequestSuccessListener(this);
        paymentFragment.setRequestSuccessListener(this);
        authServices = ApiUtils.registerCustomerService();
        String token = Paper.book().read("token");
        int userId = Paper.book().read("userId");
        String username = Paper.book().read(("username"));
        fetchUserDate(token);

    }

    private void fetchUserDate(String token) {
        authServices.getCustomer(token).enqueue(
                new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        if(response.isSuccessful()){
                            customer = response.body();
                            if(customer==null || customer.getAddress_1()==null) {
                                fragmentAddressView.setVisibility(View.VISIBLE);
                                markSelected(0);
                                fragmentPaymentView.setVisibility(View.GONE);
                            }
                            else {
                                fragmentAddressView.setVisibility(View.GONE);
                                markSelected(1);
                                fragmentPaymentView.setVisibility(View.VISIBLE);
                            }
                            return;
                        }
                        Log.w("error", response.message());
                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {

                    }
                }
        );
    }

    private void markSelected(int currentItem) {
        switch (currentItem){
            case  0:
                address.setAlpha(1f);
                payment.setAlpha(0.5f);
                complete.setAlpha(0.5f);
                addresspolygon.setImageDrawable(getResources().getDrawable(R.drawable.ic_polygon));
                paymentpolygon.setImageDrawable(getResources().getDrawable(R.drawable.ic_black_polygon));
                completePolygon.setImageDrawable(getResources().getDrawable(R.drawable.ic_black_polygon));
                addresspolygon.setAlpha(1f);
                paymentpolygon.setAlpha(0.5f);
                completePolygon.setAlpha(0.5f);
                break;
            case 1:
                payment.setAlpha(1f);
                address.setAlpha(0.5f);
                complete.setAlpha(0.5f);
                paymentpolygon.setImageDrawable(getResources().getDrawable(R.drawable.ic_polygon));
                addresspolygon.setImageDrawable(getResources().getDrawable(R.drawable.ic_black_polygon));
                completePolygon.setImageDrawable(getResources().getDrawable(R.drawable.ic_black_polygon));
                paymentpolygon.setAlpha(1f);
                addresspolygon.setAlpha(0.5f);
                completePolygon.setAlpha(0.5f);
                break;
            case 2:
                complete.setAlpha(1f);
                address.setAlpha(0.5f);
                complete.setAlpha(0.5f);
                completePolygon.setImageDrawable(getResources().getDrawable(R.drawable.ic_polygon));
                addresspolygon.setImageDrawable(getResources().getDrawable(R.drawable.ic_black_polygon));
                paymentpolygon.setImageDrawable(getResources().getDrawable(R.drawable.ic_black_polygon));
                paymentpolygon.setAlpha(1f);
                addresspolygon.setAlpha(0.5f);
                completePolygon.setAlpha(0.5f);
                break;
        }
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onRequestSuccessListener(int index) {
        switch (index){
            case 0:
                markSelected(index);
                fragmentAddressView.setVisibility(View.GONE);
                fragmentPaymentView.setVisibility(View.VISIBLE);
                break;
            case 1:
                fragmentPaymentView.setVisibility(View.VISIBLE);
                markSelected(index);
                break;
            case 2:
                fragmentPaymentView.setVisibility(View.GONE);
                fragmentCompleteView.setVisibility(View.VISIBLE);
                markSelected(index);
                break;
        }

    }
}
