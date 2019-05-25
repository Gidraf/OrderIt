package com.g_draflab.orderit.Models;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("category_id")
    String categoryId;

    @SerializedName("department_id")
    int departmentId;

    String name, description;

    public Category() {
    }

    public Category(String  categoryId, int departmentId, String name, String description) {
        this.categoryId = categoryId;
        this.departmentId = departmentId;
        this.name = name;
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
