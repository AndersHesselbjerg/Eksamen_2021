package com.example.demo.models;

import java.util.ArrayList;

public class Subproject {
    private int id;
    private String name;
    private String description;
    private int projectID;

    public Subproject(int id, String name, String description, int projectID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.projectID = projectID;
    }


    public Subproject() {


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    @Override
    public String toString() {
        return "Subproject\nName = " + name +
                "\nDescription = " + description + "\n" ;
    };
}
