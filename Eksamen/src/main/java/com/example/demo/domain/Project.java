package com.example.demo.domain;

import java.util.Date;

public class Project {
    private int id;
    private String name;
    private String description;
    private short numberOfEmployees;

    public Project(int id, String name, String description, short numberOfEmployees){
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberOfEmployees = numberOfEmployees;
    }

    public Project(){

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

    public short getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(short numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }
}
