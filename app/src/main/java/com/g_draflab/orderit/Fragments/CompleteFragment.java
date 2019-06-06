package com.g_draflab.orderit.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.g_draflab.orderit.Activities.MainActivity;
import com.g_draflab.orderit.R;

import java.util.Random;

import io.paperdb.Paper;


public class CompleteFragment extends Fragment {

    TextView orderIdTextView;
    Button backToshopbtn;

    public CompleteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Random random;
        random = new Random();
        View view =  inflater.inflate(R.layout.fragment_complete, container, false);
        orderIdTextView =  view.findViewById(R.id.orderId_tv);
        Paper.init(getActivity());
        String orderid = Paper.book().read("orderId") =="null"? String.valueOf(random.nextInt()) :Paper.book().read("orderId");
        orderIdTextView.setText(orderid);
        backToshopbtn = view.findViewById(R.id.back_to_shop_btn);
        backToshopbtn.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), MainActivity.class));
            if(getActivity()!=null) {
                getActivity().finish();
            }
        });
        return view;
    }

}
