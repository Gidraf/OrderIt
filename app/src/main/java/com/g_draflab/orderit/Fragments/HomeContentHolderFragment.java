package com.g_draflab.orderit.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.g_draflab.orderit.Adapter.ProductRecyclerViewsAdapter;
import com.g_draflab.orderit.Adapter.ProductsFragmentAdapter;
import com.g_draflab.orderit.Interfaces.GetCurrentPagePositionListener;
import com.g_draflab.orderit.Interfaces.OnBackPresspressed;
import com.g_draflab.orderit.Models.Category;
import com.g_draflab.orderit.Models.Department;
import com.g_draflab.orderit.Models.Product;
import com.g_draflab.orderit.Models.ProductResponse;
import com.g_draflab.orderit.R;
import com.g_draflab.orderit.Retrofit.ApiUtils;
import com.g_draflab.orderit.Retrofit.CategoryService;
import com.g_draflab.orderit.Retrofit.DepartmentsServices;
import com.g_draflab.orderit.Retrofit.ProductsServices;
import com.g_draflab.orderit.Utils.Constants;

import net.skoumal.fragmentback.BackFragment;

import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * It holds shop_view content
 */
public class HomeContentHolderFragment extends Fragment implements View.OnClickListener, BackFragment {

    TextView menWear, femaleWear, offerWear, menWearDescription, femaleWearDescription, offerWearDescription;
    DepartmentsServices departmentsServices;
    private int shortAnimationDuration;
    public  LinearLayout menWearView, womenWearView, promotextView;
    public RelativeLayout offerView, productsView;
    public Button offerShopButton;
    ProductsFragmentAdapter fragmentAdapter;
    List<Product> productList;
    Department regional;
    Department nature;
    Department seasonal;
    OnBackPresspressed backPresspressed;
    CategoryService categoryService;
    TabLayout productsCategoriesTabLayout;
    SpotsDialog.Builder dialog;
    AlertDialog progress;
    ViewPager productsViewPager;
    int currentDepartment;

    public HomeContentHolderFragment() {

    }

    public void setBackPresspressed(OnBackPresspressed backPresspressed) {
        this.backPresspressed = backPresspressed;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_content2, container, false);
        departmentsServices = ApiUtils.departmentsServices();
        menWear =  view.findViewById(R.id.men_wear);
        femaleWear =  view.findViewById(R.id.women_wear);
        categoryService = ApiUtils.categoryService();
        productsViewPager = view.findViewById(R.id.products_list_view_holder_view_pager);
        dialog = new SpotsDialog.Builder().setContext(getActivity());
        dialog.setTheme(R.style.sports_alert_dialog);
        productsCategoriesTabLayout = view.findViewById(R.id.products_list_tab_layout);
        offerWear =  view.findViewById(R.id.offer_Text);
        menWearDescription =  view.findViewById(R.id.men_wear_description);
        femaleWearDescription =  view.findViewById(R.id.women_wear_description);
        offerWearDescription =  view.findViewById(R.id.offer_text_description);
        menWearView = view.findViewById(R.id.men_wear_view);
        womenWearView = view.findViewById(R.id.women_wear_view);
        offerView = view.findViewById(R.id.offer_view);
        offerShopButton = view.findViewById(R.id.offer_Shop_button);
        promotextView = view.findViewById(R.id.promo_text_linear_layout);
        productsView = view.findViewById(R.id.products_view);
        shortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);
        menWearView.setOnClickListener(this);
        womenWearView.setOnClickListener(this);
        promotextView.setOnClickListener(this);
        offerView.setOnClickListener(this);
        // Inflate the layout for this fragment
        fetchDepartmentsData();
        return view;
    }

    private void fetchDepartmentsData() {

        departmentsServices.getAllDepartments().enqueue(new Callback<List<Department>>() {
            @Override
            public void onResponse(Call<List<Department>> call, Response<List<Department>> response) {
                if(response.isSuccessful()){
                    setHomePageData(response.body());
                    return;
                }

                Toast.makeText(getActivity(), "an error occurred", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Department>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setHomePageData(List<Department> body) {
        regional = body.get(0);
        nature = body.get(1);
        seasonal = body.get(2);

        menWear.setText(regional.getName());
        menWearDescription.setText(regional.getDescription());
        femaleWear.setText(nature.getName());
        femaleWearDescription.setText(nature.getDescription());
        offerWear.setText(seasonal.getName());
        offerWearDescription.setText(seasonal.getDescription());
    }

    @Override
    public void onClick(View v) {
        if(v==menWearView){
            offerView.animate().alpha(0f)
                    .setDuration(shortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            offerView.setVisibility(View.GONE);
                        }
                    });
            promotextView.animate().alpha(0f)
                    .setDuration(shortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            promotextView.setVisibility(View.GONE);
                        }
                    });
            womenWearView.animate().alpha(0f)
                    .setDuration(shortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            womenWearView.setVisibility(View.GONE);
                        }
                    });
            if(regional != null) {
                Constants.currentDepartment = regional.getDepartment_id();
               getAllCategories(Constants.currentDepartment);
            }
        }
        if(v==womenWearView){
            offerView.animate().alpha(0f)
                    .setDuration(shortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            offerView.setVisibility(View.GONE);
                        }
                    });
            promotextView.animate().alpha(0f)
                    .setDuration(shortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            promotextView.setVisibility(View.GONE);
                        }
                    });
            menWearView.animate().alpha(0f)
                    .setDuration(shortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            menWearView.setVisibility(View.GONE);
                        }
                    });
            if(nature != null) {
                Constants.currentDepartment = nature.getDepartment_id();
                getAllCategories(Constants.currentDepartment);
            }
        }
        if(v==offerView){
            offerShopButton.animate().alpha(0f)
                    .setDuration(shortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            offerShopButton.setVisibility(View.GONE);
                        }
                    });
            womenWearView.animate().alpha(0f)
                    .setDuration(shortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            womenWearView.setVisibility(View.GONE);
                        }
                    });
            promotextView.animate().alpha(0f)
                    .setDuration(shortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            promotextView.setVisibility(View.GONE);
                        }
                    });
            menWearView.animate().alpha(0f)
                    .setDuration(shortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            menWearView.setVisibility(View.GONE);
                        }
                    });
            if(seasonal != null) {
                Constants.currentDepartment = seasonal.getDepartment_id();
                getAllCategories(currentDepartment);
            }
        }
    }

    private void getAllCategories(int id) {
        showProgressBar();
        categoryService.getallCategries(id).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()) {
                    dismissProgrssBar();
                    Category category = new Category();
                    category.setName(getString(R.string.all_products));
                    category.setCategoryId("0");
                    List<Category> categories = response.body();
                    categories.add(0, category);
                    fragmentAdapter = new ProductsFragmentAdapter(getFragmentManager(),categories, getContext());
                    productsViewPager.setAdapter(fragmentAdapter);
                    productsCategoriesTabLayout.setupWithViewPager(productsViewPager);
                    productsView.setVisibility(View.VISIBLE);
                    return;
                }
                dismissProgrssBar();
                Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                dismissProgrssBar();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showProgressBar(){
        dialog.setMessage(getResources().getString(R.string.loading));
        dialog.setCancelable(false);
        progress = dialog.build();
        progress.show();
    }

    public void dismissProgrssBar(){
        if(progress.isShowing()){
            progress.dismiss();
        }
    }

    @Override
    public boolean onBackPressed() {
        returnHomeScreen();
        return true;
    }

    private void returnHomeScreen() {
            offerView.animate().alpha(1f)
                    .setDuration(shortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            offerView.setVisibility(View.VISIBLE);
                        }
                    });

            promotextView.animate().alpha(1f)
                    .setDuration(shortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            promotextView.setVisibility(View.VISIBLE);
                        }
                    });

            menWearView.animate().alpha(1f)
                    .setDuration(shortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            menWearView.setVisibility(View.VISIBLE);
                        }
                    });
            womenWearView.animate().alpha(1f)
                    .setDuration(shortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            womenWearView.setVisibility(View.VISIBLE);
                        }
                    });
                productsView.setVisibility(View.GONE);

    }


    @Override
    public int getBackPriority() {
        return 0;
    }
}
