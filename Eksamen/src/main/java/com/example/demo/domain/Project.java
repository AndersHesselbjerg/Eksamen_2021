package com.example.demo.domain;

import java.util.Date;

public class Project {
    private int id;
    private String name;
    private Date deadline;
    private String description;
    private short numberOfEmployees;

    public Project(int id, String name, Date deadline, String description, short numberOfEmployees){
        this.id = id;
        this.name = name;
        this.deadline = deadline;
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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
