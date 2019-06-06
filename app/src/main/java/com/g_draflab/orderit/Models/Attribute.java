package com.g_draflab.orderit.Models;

import com.google.gson.annotations.SerializedName;

public class Attribute {
    @SerializedName("attribute_name")
    String attributename;
    @SerializedName("attribute_value")
    String attributeValue;
    @SerializedName("attribute_value_id")
    int attributeValueId;

    public Attribute(String attributename, String attributeValue, int attributeValueId) {
        this.attributename = attributename;
        this.attributeValue = attributeValue;
        this.attributeValueId = attributeValueId;
    }

    public String getAttributename() {
        return attributename;
    }

    public void setAttributename(String attributename) {
        this.attributename = attributename;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public int getAttributeValueId() {
        return attributeValueId;
    }

    public void setAttributeValueId(int attributeValueId) {
        this.attributeValueId = attributeValueId;
    }
}
