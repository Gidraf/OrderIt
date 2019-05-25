package com.g_draflab.orderit.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.g_draflab.orderit.R;

import java.util.List;

public class NavMenuItemAdapter extends BaseAdapter {

    TextView item_menu_name;
    List<String> menuItems;
    Context context;

    public NavMenuItemAdapter(List<String> menuItems, Context context) {
        this.menuItems = menuItems;
        this.context = context;
    }



    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String menu = menuItems.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.nav_item_menu, parent, false);
        item_menu_name = convertView.findViewById(R.id.nav_item_menu_text_view);
        item_menu_name.setText(menu);
        if(menu.equals(context.getString(R.string.logout))){
            item_menu_name.setTextColor(Color.parseColor("#EFB961"));
        }
        return convertView;
    }
}
