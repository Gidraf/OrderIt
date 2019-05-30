package com.g_draflab.orderit.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.g_draflab.orderit.Activities.DetailsPage;
import com.g_draflab.orderit.R;


public class Inspiration extends Fragment implements View.OnClickListener {
    ImageView fashion, life, videos;

    public Inspiration() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inspiration, container, false);
        fashion = view.findViewById(R.id.fashionView);
        life = view.findViewById(R.id.lifeView);
        videos = view.findViewById(R.id.videosView);

        videos.setOnClickListener(this);
        life.setOnClickListener(this);
        fashion.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        if(v==videos||v==life||v==fashion){
            startActivity(new Intent(getContext(), DetailsPage.class));
        }
    }
}
