package com.example.demo.models;
//Lavet af Daniel
public class Subproject {
    private int id;
    private String name;
    private String description;
    private int projectID;
    private int estimatedTime;



    public Subproject() {
    }

    public Subproject(int id, String name, String description, int projectID, int estimatedTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.projectID = projectID;
        this.estimatedTime = estimatedTime;
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

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    @Override
    public String toString() {
        return "Subproject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", projectID=" + projectID +
                ", estimatedTime=" + estimatedTime +
                '}';
    }
}
