package com.example.demo.models;

import java.util.Date;

public class Task {
    private int id;
    private String name;
    private String description;
    private Date deadline;
    private int projectIDTask;

    public Task(){

    }

    public Task(int id, String name, String description, Date deadline, int projectIDTask) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.projectIDTask = projectIDTask;
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getProjectIDTask() {
        return projectIDTask;
    }

    public void setProjectID(int projectIDTask) {
        this.projectIDTask = projectIDTask;
    }

    @Override
    public String toString() {
        return "Task{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                '}';
    }
}
