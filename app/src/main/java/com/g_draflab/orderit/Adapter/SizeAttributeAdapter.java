package com.g_draflab.orderit.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.g_draflab.orderit.Interfaces.AttributeListener;
import com.g_draflab.orderit.Models.Attribute;
import com.g_draflab.orderit.R;

import java.util.List;

public class SizeAttributeAdapter extends RecyclerView.Adapter<SizeAttributeViewHolder> {

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

    public SizeAttributeAdapter(View.OnClickListener listener, Context context) {
            this.listener = listener;
            this.context = context;
            }


    @NonNull
    @Override
    public SizeAttributeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.size_holder, viewGroup, false);
            return new SizeAttributeViewHolder(view, listener) ;
            }

    public void setAttributeList(List<Attribute> attributeList) {
            this.attributeList = attributeList;
            notifyDataSetChanged();
            }

    @Override
    public void onBindViewHolder(@NonNull SizeAttributeViewHolder colorAttributeViewHolder, int i) {
            Attribute attribute = attributeList.get(i);

            colorAttributeViewHolder.sizeText.setText(attribute.getAttributeValue());
            colorAttributeViewHolder.setClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(attributeListener!=null){
                        attributeListener.onAttrbutelistener(attribute.getAttributeValue());
                    }
                }
            });

            }

    @Override
    public int getItemCount() {
            return attributeList == null ? 0: attributeList.size();
            }

}

class SizeAttributeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View.OnClickListener clickListener;
    ImageView colorImage;
    TextView sizeText;

    public View.OnClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public SizeAttributeViewHolder(@NonNull View itemView, View.OnClickListener clickListener) {
        super(itemView);
        colorImage = itemView.findViewById(R.id.size_border_image);
        sizeText = itemView.findViewById(R.id.size_text_view);
        this.clickListener = clickListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        clickListener.onClick(v);
    }
}