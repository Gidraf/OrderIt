package com.g_draflab.orderit.Fragments;


import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.g_draflab.orderit.Adapter.NavMenuItemAdapter;
import com.g_draflab.orderit.Interfaces.OnNavigationClickedListener;
import com.g_draflab.orderit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {
    List<String> menuItems;
    ListView menuListItems;
    NavMenuItemAdapter adapter;
    public NavigationDrawerFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        menuListItems = view.findViewById(R.id.nav_menu_list);
        menuItems = new ArrayList<>();
        menuItems.add(getString(R.string.my_account));
        menuItems.add(getString(R.string.customer_support));
        menuItems.add(getString(R.string.logout));
        adapter = new NavMenuItemAdapter(menuItems, getActivity());
        menuListItems.setAdapter(adapter);
        return view;
    }
}
