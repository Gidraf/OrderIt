package com.g_draflab.orderit.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.g_draflab.orderit.Interfaces.AttributeListener;
import com.g_draflab.orderit.Models.Attribute;
import com.g_draflab.orderit.R;

import java.util.List;

public class ColorAttributeAdapter extends RecyclerView.Adapter<ColorAttributeViewHolder>  {

    View.OnClickListener listener;
    Context context;
    List<Attribute> attributeList;
    AttributeListener attributeListener;

    public AttributeListener getAttributeListener() {
        return attributeListener;
    }

    public void setAttributeListener(AttributeListener attributeListener) {
        this.attributeListener = attributeListener;
    }

    public ColorAttributeAdapter(View.OnClickListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ColorAttributeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.color_holder, viewGroup, false);
        return new ColorAttributeViewHolder(view, listener) ;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ColorAttributeViewHolder colorAttributeViewHolder, int i) {
        Attribute attribute = attributeList.get(i);
        switch (attribute.getAttributeValue()){
            case "White":
                colorAttributeViewHolder.colorImage.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                break;
            case "Black":
                colorAttributeViewHolder.colorImage.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                break;
            case "Red":
                colorAttributeViewHolder.colorImage.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                break;
            case "Orange":
                colorAttributeViewHolder.colorImage.setColorFilter(Color.parseColor("#FFA500"), PorterDuff.Mode.SRC_IN);
                break;
            case "Yellow":
                colorAttributeViewHolder.colorImage.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
                break;
            case "Green":
                colorAttributeViewHolder.colorImage.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                break;
            case "Blue":
                colorAttributeViewHolder.colorImage.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                break;
            case "Indigo":
                colorAttributeViewHolder.colorImage.setColorFilter(Color.parseColor("#4b0082"), PorterDuff.Mode.SRC_IN);
                break;
            case "Purple":
                colorAttributeViewHolder.colorImage.setColorFilter(Color.parseColor("#800080"), PorterDuff.Mode.SRC_IN);
                break;

        }
        colorAttributeViewHolder.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(attributeListener!=null){
                    attributeListener.onAttrbutelistener(attributeList.get(i).getAttributeValue());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return attributeList == null ? 0: attributeList.size();
    }
}

class ColorAttributeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View.OnClickListener clickListener;
    ImageView colorImage;

    public View.OnClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public ColorAttributeViewHolder(@NonNull View itemView, View.OnClickListener clickListener) {
        super(itemView);
        colorImage = itemView.findViewById(R.id.color_image);
        this.clickListener = clickListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        clickListener.onClick(v);
    }
}
