package com.example.demo.database;

import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.net.PortUnreachableException;
import java.sql.*;
import java.util.ArrayList;


@Repository
public class Mapper {

    public Mapper(){

    }

    public User createUser(User u) {
        Connection connection = DBManager.getConnection();
        String sqlstr = "INSERT INTO user (mail, password ) VALUES (?, ?);";
        PreparedStatement preparedStatement;
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(sqlstr);
            System.out.println(sqlstr);
            preparedStatement.setString(1, u.getMail());
            preparedStatement.setString(2, u.getPassword());
            int row = preparedStatement.executeUpdate();
            System.out.println(row);
            System.out.println("Tillykke brugeren: " + preparedStatement + " Er oprettet");
        } catch (SQLException sqlerr) {
            System.out.println("Fejl i oprettels =" + sqlerr);
        }
        return user;
    }

    public User logIn(String mail, String password) {
        Connection connection = DBManager.getConnection();
        String searchLog = "select * FROM user WHERE mail = ? and password = ?; ";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(searchLog);
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() == false) {
                return user;
            }
            user = new User(resultSet.getInt("id"),resultSet.getString("mail"), resultSet.getString("password"));


        } catch (SQLException sqlerr) {
            System.out.println("Fejl i søgning = " + sqlerr.getMessage());
        }

        return user;
    }


    public Boolean userExist(String mail) {
        Connection connection = DBManager.getConnection();
        String searchString = "select * FROM user WHERE mail = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            preparedStatement = connection.prepareStatement(searchString);
            preparedStatement.setString(1, mail);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Dette login passer ikke: " + e.getMessage());
            return false;
        }

    }


    
    public Project createProject(Project project){
        Connection connection = DBManager.getConnection();
        String sqlstr = "INSERT INTO projects(name, description, numberOfEmployees, deadline) VALUES(?, ?, ?, ?)";
        System.out.println("Så langt så godt");
        PreparedStatement preparedStatement;
        try{
            preparedStatement = connection.prepareStatement(sqlstr);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setInt(3, project.getNumberOfEmployees());
            preparedStatement.setObject(4,  project.getDeadline());
            int row = preparedStatement.executeUpdate();
            System.out.println(row);
            System.out.println("Tillykke projekt: " + preparedStatement + ". Blev oprettet");
        } catch(SQLException sqlerror){
            System.out.println("Fejl i oprettelse af projekt=" + sqlerror);
        }
        return project;
    }


    public ArrayList<Project> getProjects(){
        ArrayList<Project> projectList = new ArrayList<>();
        try {
            Connection connection = DBManager.getConnection();
            String sqlproject = "SELECT * FROM projects";
            String sqlSubproject = "SELECT * FROM subprojects WHERE mainProject = projects.name";
            PreparedStatement prepareStatement;
            prepareStatement = connection.prepareStatement(sqlproject);
            ResultSet resultSet = prepareStatement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String projectName = resultSet.getString("name");
                String description = resultSet.getString("description");
                int numberOfEmployees = resultSet.getInt("numberOfEmployees");
                Date deadline = resultSet.getDate("deadlineDate"); // Grunden til det ikke virkede før, er at
                //Time deadlineTime = resultSet.getTime("currentTime");

                Project project = new Project(id, projectName, description, numberOfEmployees, deadline);
                projectList.add(project);
            }
        } catch(SQLException exception){
            exception.printStackTrace();
            System.out.println("Fejl i nedhentning af projekter");
        }
        return projectList;
    }



    public Boolean loginCredentialsCorrect(String mail, String password) {
        Connection connection = DBManager.getConnection();
        String searchStr = "SELECT * FROM user where mail = ? and password = ? ";
        PreparedStatement preparedStatement;
        int res = -1;
        String theMail = mail;
        String thePassword = password;
        ResultSet resset;
        Boolean exist = false;
        try {
            preparedStatement = connection.prepareStatement(searchStr);
            preparedStatement.setString(1, theMail);
            preparedStatement.setString(2, thePassword);
            System.out.println(searchStr);
            System.out.println(preparedStatement);
            resset = preparedStatement.executeQuery();
            if (resset.next()) {
                String str = "" + resset.getObject(1);
                res = Integer.parseInt(str);
                System.out.println("fundet id: = " + res);
            }
            if (res == 1) {
                exist = true;
                System.out.println("Id " + res + "Eksistere ");
            } else {
            }

        } catch (SQLException sqlerr) {
            System.out.println("fejl i exist = " + sqlerr.getMessage());
        }

        return exist;
    }

}
