package com.example.demo.domain;

public class Subproject {
    private int id;
    private String name;
    private String description;
    private Project mainProject;
    private int projectID;

    public Subproject(int id, String name, String description, Project mainProject, int projectID){
        this.id = id;
        this.name = name;
        this.description = description;
        this.mainProject = mainProject;
        this.projectID = projectID;
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

    public Project getMainProject() {
        return mainProject;
    }

    public void setMainProject(Project mainProject) {
        this.mainProject = mainProject;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    @Override
    public String toString() {
        return "Subproject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", mainProject=" + mainProject +
                '}';
    }
}
