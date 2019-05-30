package com.g_draflab.orderit.Fragments;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.g_draflab.orderit.Activities.SignInActivity;
import com.g_draflab.orderit.Activities.SignUpActivity;
import com.g_draflab.orderit.Interfaces.OnRequestSuccessListener;
import com.g_draflab.orderit.Models.CartTotal;
import com.g_draflab.orderit.Models.Customer;
import com.g_draflab.orderit.Models.OrderId;
import com.g_draflab.orderit.Models.StripeResponse;
import com.g_draflab.orderit.R;
import com.g_draflab.orderit.Retrofit.ApiUtils;
import com.g_draflab.orderit.Retrofit.AuthServices;
import com.g_draflab.orderit.Retrofit.CartServices;
import com.g_draflab.orderit.Retrofit.OrderServices;
import com.g_draflab.orderit.Retrofit.StripeServices;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {

    OnRequestSuccessListener requestSuccessListener;
    AuthServices authServices;
    TextView stateTv, cityTv, countrytTv, address1, address2;
    EditText cardNumberEdittxt, cardMontheditTxt, cvcEditTRext, cartYearExpiryEditText;
    Button payNowbtn;
    SpotsDialog.Builder dialog;
    AlertDialog builder;
    Card card;
    OrderServices orderServices;
    Customer customer;
    CartServices cartServices;
    CartTotal cartTotal;
    StripeServices stripeServices;
    Stripe stripe;

    public OnRequestSuccessListener getRequestSuccessListener() {
        return requestSuccessListener;
    }

    public void setRequestSuccessListener(OnRequestSuccessListener requestSuccessListener) {
        this.requestSuccessListener = requestSuccessListener;
    }

    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        authServices = ApiUtils.registerCustomerService();
        stripe = new Stripe(getContext(), "pk_test_Udul6jdjVOJBAGQySeesvvWs0068gu2D2O");
        Paper.init(getContext());
        stateTv = view.findViewById(R.id.state_textview);
        orderServices = ApiUtils.orderServices();
        dialog = new SpotsDialog.Builder().setContext(getActivity());
        dialog.setTheme(R.style.sports_alert_dialog);
        countrytTv = view.findViewById(R.id.country_textview);
        cityTv = view.findViewById(R.id.city_textview);
        cartServices = ApiUtils.cartServices();
        stripeServices = ApiUtils.stripeServices();
        cardNumberEdittxt = view.findViewById(R.id.card_number);
        cardMontheditTxt = view.findViewById(R.id.exiry_date_month);
        cvcEditTRext = view.findViewById(R.id.cvc_code);
        cartYearExpiryEditText = view.findViewById(R.id.exiry_date_year);
        payNowbtn = view.findViewById(R.id.payBtn);
        address1 = view.findViewById(R.id.address_line1_tv);
        address2 = view.findViewById(R.id.address_line2_tv);
        String token = Paper.book().read("token");
        String cartId = Paper.book().read("cartId");
        payNowbtn.setOnClickListener(v -> checkoutProduct(cartId, token));
        getCartTotal(cartId);
        fetchUserAddress(token);
        return view;
    }

    private void getCartTotal(String cartId) {
        cartServices.getCartTotalAmount(cartId).enqueue(new Callback<CartTotal>() {
            @Override
            public void onResponse(Call<CartTotal> call, Response<CartTotal> response) {
                if(response.isSuccessful()){
                    cartTotal = response.body();
                }
            }

            @Override
            public void onFailure(Call<CartTotal> call, Throwable t) {

            }
        });
    }

    /****
     * Checkout product an complete the payment
     *
     * ****/

    private void checkoutProduct(String cartId, String token) {
        String cardNumber = cardNumberEdittxt.getText().toString();
        String monthdate = cardMontheditTxt.getText().toString();
        String yeardate = cartYearExpiryEditText.getText().toString();
        String cvcCode = cvcEditTRext.getText().toString();

        if(cardNumber.isEmpty()){
            Toast.makeText(getContext(),
                    "Please enter Card number",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(monthdate.isEmpty()){
            Toast.makeText(getContext(),
                    "Please enter Month date number",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(yeardate.isEmpty()){
            Toast.makeText(getContext(),
                    "Please enter year date number",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(cvcCode.isEmpty()){
            Toast.makeText(getContext(),
                    "Please enter cvc code num" +
                            "ber", Toast.LENGTH_SHORT).show();
            return;
        }

        card = new Card(cardNumber,Integer.parseInt(monthdate),Integer.parseInt(yeardate),cvcCode);
        card.validateCard();

        if(!card.validateCVC()){
            Toast.makeText(getContext(),
                    "Please enter a valid cvc code number",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if(!card.validateCard()){
            Toast.makeText(getContext(), "Please enter a valid Card number", Toast.LENGTH_SHORT).show();
        }

//      if everything is fine thw we first save the order to get the order Id
        postOrder(cartId, token);



    }

    /***
     *
     * Get charges from Stripe
     * ***/
    private void makeChargesTOStripe(int orderId, String cartId) {
        showProgressBar();
        stripe.createToken(
                card,
                new TokenCallback() {
                    public void onSuccess(Token token) {
                        dismissProgrssBar();
                        if(cartTotal!=null){
                            postTokenServer(token, cartTotal.getTotalAmount(), orderId);
                            return;
                        }
                        dismissProgrssBar();
                        Toast.makeText(getContext(), "An error occured please try again", Toast.LENGTH_LONG).show();
                        getCartTotal(cartId);
                    }
                    public void onError(Exception error) {
                        dismissProgrssBar();
                        // Show localized error message
                        Toast.makeText(getContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );
    }

    private void postTokenServer(Token token, String totalAmount, int orderid) {
        double totalDouble = Double.parseDouble(totalAmount);
        showProgressBar();
        stripeServices.postCharges(token.getId(),orderid,"nothing for now",(int)totalDouble).enqueue(new Callback<StripeResponse>() {
            @Override
            public void onResponse(Call<StripeResponse> call, Response<StripeResponse> response) {
                    dismissProgrssBar();
                        Paper.book().write("orderId", String.valueOf(orderid));
                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setCancelable(false);
                        sweetAlertDialog.setContentText("Successfully Paid");
                        sweetAlertDialog.setConfirmText("Complete").setConfirmClickListener(
                                sweetAlertDialog1 -> {
                                    Paper.book().delete("cartId");
                                    requestSuccessListener.onRequestSuccessListener(2);
                                    sweetAlertDialog1.dismiss();
                                }
                        ).show();

            }

            @Override
            public void onFailure(Call<StripeResponse> call, Throwable t) {
                dismissProgrssBar();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postOrder(String cartId, String token) {
        showProgressBar();
        orderServices.getOrderId(cartId,
                customer!=null? customer.getShipping_region_id():0,1,token).enqueue(new Callback<OrderId>() {
            @Override
            public void onResponse(Call<OrderId> call, Response<OrderId> response) {
                if(response.isSuccessful()){
                    dismissProgrssBar();
                    OrderId id = response.body();
                    makeChargesTOStripe(id.getOrderId(), cartId);
                    return;
                }
                dismissProgrssBar();
                Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<OrderId> call, Throwable t) {
                dismissProgrssBar();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchUserAddress(String token) {
        authServices.getCustomer(token).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if(response.isSuccessful()){
                    customer = response.body();
                    stateTv.setText(customer.getRegion());
                    cityTv.setText(customer.getCity());
                    countrytTv.setText(customer.getCountry());
                    address1.setText(customer.getAddress_1());
                    address2.setText(customer.getAddress_2());
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

            }
        });
    }

    public void showProgressBar(){
        dialog.setMessage(getResources().getString(R.string.loading));
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
