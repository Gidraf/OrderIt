package com.g_draflab.orderit.Fragments;


import android.app.AlertDialog;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.g_draflab.orderit.Interfaces.OnRequestSuccessListener;
import com.g_draflab.orderit.Models.Customer;
import com.g_draflab.orderit.Models.Shipping;
import com.g_draflab.orderit.R;
import com.g_draflab.orderit.Retrofit.ApiUtils;
import com.g_draflab.orderit.Retrofit.AuthServices;
import com.g_draflab.orderit.Retrofit.ShippingRegionService;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;

import net.skoumal.fragmentback.BackFragment;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class AddressFragment extends android.support.v4.app.Fragment {
    OnRequestSuccessListener requestSuccessListener;
    MaterialEditText usernameEdittxt, addressline1Edittxt, addressline2Edittxt, cityEdittxt, zipEdittxt, countryEdittxt, stateEdittxt;
    Button saveAddressBtn;
    SpotsDialog.Builder dialog;
    AlertDialog builder;
    MaterialSpinner shippingRegion;
    ShippingRegionService shippingRegionService;
    AuthServices authServices;
    ArrayAdapter<CharSequence> arrayAdapter;
    public OnRequestSuccessListener getRequestSuccessListener() {
        return requestSuccessListener;
    }

    public void setRequestSuccessListener(OnRequestSuccessListener requestSuccessListener) {
        this.requestSuccessListener = requestSuccessListener;
    }

    public AddressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_address, container, false);
        usernameEdittxt = view.findViewById(R.id.username_edit_text);
        addressline1Edittxt = view.findViewById(R.id.address_line1);
        addressline2Edittxt = view.findViewById(R.id.address_line2);
        shippingRegion = view.findViewById(R.id.shpping_region_spinner);
        cityEdittxt = view.findViewById(R.id.city_edit_text);
        stateEdittxt = view.findViewById(R.id.state_edit_text);
        zipEdittxt = view.findViewById(R.id.zip_edit_text);
        authServices = ApiUtils.registerCustomerService();
        Paper.init(getActivity());
        countryEdittxt = view.findViewById(R.id.country_edit_text);
        saveAddressBtn = view.findViewById(R.id.save_address_btn);
        dialog = new SpotsDialog.Builder().setContext(getActivity());
        shippingRegionService = ApiUtils.shippingRegionService();
        Paper.init(getActivity());
        String username = Paper.book().read("username");
        usernameEdittxt.setText(username);
        dialog.setTheme(R.style.sports_alert_dialog);
        saveAddressBtn.setOnClickListener(v -> updateCustomerInformation());
        getShoppingRegions();
        return view;
    }

    private void getShoppingRegions() {
        shippingRegionService.getShippingRegions().enqueue(new Callback<List<Shipping>>() {
            @Override
            public void onResponse(Call<List<Shipping>> call, Response<List<Shipping>> response) {
                List<String> regions = new ArrayList<>();
                for (Shipping shipping: response.body()){
                    regions.add(shipping.getShippingRegion());
                }
                shippingRegion.setItems(regions);
                return;
            }

            @Override
            public void onFailure(Call<List<Shipping>> call, Throwable t) {

            }
        });
    }


    private void updateCustomerInformation() {
        String username = usernameEdittxt.getText().toString();
        String addressline1 = addressline1Edittxt.getText().toString();
        String addressline2 = addressline2Edittxt.getText().toString();
        String city = cityEdittxt.getText().toString();
        String state = stateEdittxt.getText().toString();
        String country = countryEdittxt.getText().toString();
        String zipCode = zipEdittxt.getText().toString();
        int shippingId = shippingRegion.getSelectedIndex() + 1;
        if(username.isEmpty()){
            usernameEdittxt.setFloatingLabelText(getResources().getString(R.string.empty_username));
            usernameEdittxt.setFloatingLabelTextColor(Color.RED);
            return;
        }

        if(addressline1.isEmpty()){
            addressline1Edittxt.setFloatingLabelText(getResources().getString(R.string.empty_username));
                    addressline1Edittxt.setFloatingLabelTextColor(Color.RED);
            return;
        }

        if(addressline2.isEmpty()){
            addressline2Edittxt.setFloatingLabelText(getResources().getString(R.string.empty_username));
            addressline2Edittxt.setFloatingLabelTextColor(Color.RED);
            return;
        }

        if(city.isEmpty()){
            cityEdittxt.setFloatingLabelText(getResources().getString(R.string.empty_username));
            cityEdittxt.setFloatingLabelTextColor(Color.RED);
            return;
        }
        if(state.isEmpty()){
            stateEdittxt.setFloatingLabelText(getResources().getString(R.string.empty_username));
            stateEdittxt.setFloatingLabelTextColor(Color.RED);
            return;
        }

        if(zipCode.isEmpty()){
            zipEdittxt.setFloatingLabelText(getResources().getString(R.string.empty_username));
            zipEdittxt.setFloatingLabelTextColor(Color.RED);
            return;
        }

        if(country.isEmpty()){
            countryEdittxt.setFloatingLabelText(getResources().getString(R.string.empty_username));
                    countryEdittxt.setFloatingLabelTextColor(Color.RED);
            return;
        }
        String token = Paper.book().read("token");
        showProgressBar();
        authServices.editCustomerAddress(addressline1,addressline2,city,state,zipCode,country, shippingId, token).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if(response.isSuccessful()) {
                    dismissProgrssBar();
                        requestSuccessListener.onRequestSuccessListener(1);
                    return;
                }
                dismissProgrssBar();
                Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                dismissProgrssBar();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
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
