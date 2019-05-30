package com.g_draflab.orderit.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.g_draflab.orderit.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstIntroFragment extends Fragment {


    public FirstIntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_first, container, false);

        return view;
    }

}
