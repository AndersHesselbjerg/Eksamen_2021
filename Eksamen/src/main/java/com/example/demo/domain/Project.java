package com.example.demo.domain;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class Project {
    private int id;
    private String name;
    private String description;
    private short numberOfEmployees;
    private Date deadlineDate;
    private Time deadlineTime;

    public Project(int id, String name, String description, short numberOfEmployees, Date deadlineDate, Time deadlineTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberOfEmployees = numberOfEmployees;
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
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

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Time getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(Time deadlineTime) {
        this.deadlineTime = deadlineTime;
    }
}