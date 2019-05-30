package com.g_draflab.orderit.Fragments;

import android.app.AlertDialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.g_draflab.orderit.Adapter.BottomNavigationAdapter;
import com.g_draflab.orderit.Interfaces.OnNavigationClickedListener;
import com.g_draflab.orderit.Models.CartId;
import com.g_draflab.orderit.Models.CartResponse;
import com.g_draflab.orderit.Models.Department;
import com.g_draflab.orderit.R;
import com.g_draflab.orderit.Retrofit.ApiUtils;
import com.g_draflab.orderit.Retrofit.CartServices;
import com.g_draflab.orderit.Retrofit.DepartmentsServices;
import com.g_draflab.orderit.Utils.Constants;

import net.skoumal.fragmentback.BackFragment;
import net.skoumal.fragmentback.BackFragmentAppCompatActivity;
import net.skoumal.fragmentback.BackFragmentFragmentActivity;

import java.util.List;
import java.util.Objects;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeContentFragment extends Fragment {

    OnNavigationClickedListener clickedListener;
    ViewPager contentViewpager;
    BottomNavigationAdapter navigationAdapter;
    TabLayout bottomNavigationTablayout;
    RelativeLayout parentView;
    CartServices cartServices;
    ImageView bagBtn;
    CardView bagHolder;
    TextView cartCounter;


    public HomeContentFragment() {

    }

    public void setClickedListener(OnNavigationClickedListener listener){
        this.clickedListener =  listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home_content, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar_main);
        toolbar.setTitle("");
        Paper.init(Objects.requireNonNull(getActivity()));
        bagBtn = view.findViewById(R.id.bag_btn);
        bagHolder = view.findViewById(R.id.nav_cart_counter_holder);
        cartCounter = view.findViewById(R.id.bottomNavigation_cart_counter);
        bottomNavigationTablayout = view.findViewById(R.id.bottom_navigation_tab_layout);
        parentView = view.findViewById(R.id.parent_view);
        cartServices = ApiUtils.cartServices();
        String cartId =  Paper.book().read("cartId");
        if(cartId==null) {
            cartServices.getCartId().enqueue(new Callback<CartId>() {
                @Override
                public void onResponse(Call<CartId> call, Response<CartId> response) {
                    if (response.isSuccessful()) {
                        Paper.book().write("cartId", response.body().getCartId());
                    }
                }

                @Override
                public void onFailure(Call<CartId> call, Throwable t) {

                }
            });
        }
        if(cartId!=null){
            getCartItems(cartId);
        }
        ImageView homeImage = view.findViewById(R.id.home_image);
        homeImage.setOnClickListener(v -> {
            if(clickedListener !=null) {
                clickedListener.isNavigationDrawerClicked(true);
            }
        });
        bagBtn.setOnClickListener(v -> {
            contentViewpager.setCurrentItem(2);
        });
        navigationAdapter = new BottomNavigationAdapter(getActivity().getSupportFragmentManager(), getContext());
        contentViewpager = view.findViewById(R.id.content_view_pager);
        contentViewpager.setAdapter(navigationAdapter);
        bottomNavigationTablayout.setupWithViewPager(contentViewpager);
        for (int i = 0; i < bottomNavigationTablayout.getTabCount(); i++) {
            TabLayout.Tab tab = bottomNavigationTablayout.getTabAt(i);
            View tabView = navigationAdapter.getTabView(i);
            tab.setCustomView(tabView);
        }
        return view;
    }

    private void getCartItems(String cartId) {
        cartServices.getCartItems(cartId).enqueue(new Callback<List<CartResponse>>() {
            @Override
            public void onResponse(Call<List<CartResponse>> call, Response<List<CartResponse>> response) {
                if(response.isSuccessful()){
                    if(response.body().size()>0){
                        bagHolder.setVisibility(View.VISIBLE);
                        cartCounter.setText(String.valueOf(response.body().size()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {

            }
        });
    }

}
