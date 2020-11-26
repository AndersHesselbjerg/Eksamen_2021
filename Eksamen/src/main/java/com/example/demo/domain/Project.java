package com.example.demo.domain;

import java.sql.Date;
import java.time.LocalDate;

public class Project {
    private int id;
    private String name;
    private String description;
    private short numberOfEmployees;
    private LocalDate deadline;

    public Project(int id, String name, String description, short numberOfEmployees, LocalDate deadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberOfEmployees = numberOfEmployees;
        this.deadline = deadline;
    }

    public Project() {

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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}