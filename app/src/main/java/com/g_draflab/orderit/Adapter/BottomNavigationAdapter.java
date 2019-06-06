package com.g_draflab.orderit.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.g_draflab.orderit.Fragments.BagFragment;
import com.g_draflab.orderit.Fragments.HomeContentFragment;
import com.g_draflab.orderit.Fragments.HomeContentHolderFragment;
import com.g_draflab.orderit.Fragments.Inspiration;
import com.g_draflab.orderit.R;

public class BottomNavigationAdapter extends FragmentPagerAdapter {

    Context context;

    public BottomNavigationAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    private String tabTitles[] = new String[] { "Shop", "Inspiration", "bag","Stores","More" };
    private int[] navigationIcon = new int[] {R.drawable.ic_shop_bottom_navigation_icon_selected,
            R.drawable.ic_bottom_navigation_inspiration,
            R.drawable.ic_bag_nav_icon,
            R.drawable.ic_bottom_navigation_stores_icon,
            R.drawable.ic_more_circle_dots
    };
    public BottomNavigationAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new HomeContentHolderFragment();
            case 1:
                return new Inspiration();
            case 2:
                return new BagFragment();
            default:
                return new HomeContentHolderFragment();
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
    public View getTabView(int position){
        if(position==2){
            View v = LayoutInflater.from(context).inflate(R.layout.bag_layout,null );
            return v;
        }
        View v = LayoutInflater.from(context).inflate(R.layout.bottom_navigation_layout,null );
        LinearLayout bottomNavigationView = v.findViewById(R.id.bottom_nav_view);
        TextView tv = v.findViewById(R.id.bottom_navigation_title);
        tv.setText(tabTitles[position]);
        ImageView img = v.findViewById(R.id.bottom_navigation_icon);
        img.setImageResource(navigationIcon[position]);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                175, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 0);
        bottomNavigationView.setLayoutParams(layoutParams);
        return v;
    }


}
