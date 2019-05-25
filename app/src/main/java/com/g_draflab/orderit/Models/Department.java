package com.g_draflab.orderit.Models;

public class Department {
    int department_id;
    String name;
    String description;

    public Department() {
    }

    public Department(int department_id, String name, String description) {
        this.department_id = department_id;
        this.name = name;
        this.description = description;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
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
