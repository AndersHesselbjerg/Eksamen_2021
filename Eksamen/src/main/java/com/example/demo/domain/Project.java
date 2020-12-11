package com.example.demo.domain;

import com.example.demo.database.Mapper;

import java.sql.Date;
import java.util.ArrayList;

public class Project {
    private int id;
    private String name;
    private String description;
    private int numberOfEmployees;
    private Date deadline;
    private ArrayList<Subproject> subprojects;
    private String ofSubprojects;
    //private int userID;
    private ArrayList<Integer> userID;
    private int subID;
    private String subName;
    private String subDescription;
    private int subProjectID;
    private Date saved;

    Mapper mapper = new Mapper();

    public Project() { // Grunden til at vi laver en tom konstructor, så Spring laver instanser som der skal bruges i systemet

    }

    public Project(int id,String name, String description, int numberOfEmployees, Date deadline, Date saved) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberOfEmployees = numberOfEmployees;
        this.deadline = deadline;
        this.saved = saved;
        //this.subprojects = mapper.getSubprojects(id, this);
    }

    public Project(int id, String name, String description, int numberOfEmployees, Date deadline, ArrayList<Subproject> subprojects, Date saved) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberOfEmployees = numberOfEmployees;
        this.deadline = deadline;
        this.subprojects = subprojects;
        this.saved = saved;
    }
    public Project(int subID, String subName, String subDescription, int subProjectID){
        this.subID = subID;
        this.subName = subName;
        this.subDescription = subDescription;
        this.subProjectID = subProjectID;
    }

    public Project(String name, String description, int numberOfEmployees, Date deadline) {
        this.name = name;
        this.description = description;
        this.numberOfEmployees = numberOfEmployees;
        this.deadline = deadline;
        //this.subprojects = mapper.getSubprojects(id, this);
    }

    public Project(String name, String description, int numberOfEmployees, Date deadline, ArrayList userID) {
        this.name = name;
        this.description = description;
        this.numberOfEmployees = numberOfEmployees;
        this.deadline = deadline;
        //this.subprojects = mapper.getSubprojects(id, this);
        this.userID = userID;
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

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public ArrayList<Subproject> getSubprojects() {
        return subprojects;
    }

    public void setSubprojects(ArrayList<Subproject> subprojects) {
        this.subprojects = subprojects;
    }

    public ArrayList<Integer> getUserID() { return userID; }

    public void setUserID(ArrayList<Integer> userID) {
        this.userID = userID;
    }

    public Subproject getSubprojectByID(){
        return subprojects.get(id);
    }
    public int getSubprojectsName(){
        return subprojects.get(id).getProjectID();
    }


    //public void setUserID(int userID) { this.userID = userID;


    public int getSubID() {
        return subID;
    }

    public void setSubID(int subID) {
        this.subID = subID;
    }

    public String getSubDescription() {
        return subDescription;
    }

    public void setSubDescription(String subDescription) {
        this.subDescription = subDescription;
    }

    public int getSubProjectID() {
        return subProjectID;
    }

    public void setSubProjectID(int subProjectID) {
        this.subProjectID = subProjectID;
    }

    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }


    public Date getSaved() {
        return saved;
    }

    public void setSaved(Date saved) {
        this.saved = saved;
    }

    @Override
    public String toString() {
        return "Project{" +
                ",name=' " + name + '\'' +
                ", description=' " + description + '\'' +
                ", numberOfEmployees= " + numberOfEmployees +
                '}';
    }
}