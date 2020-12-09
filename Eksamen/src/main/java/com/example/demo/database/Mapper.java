package com.example.demo.database;

import com.example.demo.domain.Project;
import com.example.demo.domain.Subproject;
import com.example.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.net.PortUnreachableException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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


    public void deleteUser(int id){
        Connection connection = DBManager.getConnection();
        String sqlRemove = "DELETE FROM user WHERE id = '?' ";
        PreparedStatement preparedStatement;
        String userIDstr = "" + id;
        User user = null;
        try{
            preparedStatement = connection.prepareStatement(sqlRemove);
            preparedStatement.setString(id, userIDstr );
            preparedStatement.execute(sqlRemove);
            System.out.println("Tillykke bruger: " + preparedStatement + " er blevet slettet");
        } catch(SQLException sqlerr){
            System.out.println("Fejl =" + sqlerr);
        }
    }

    public User logIn(String mail, String password) {
        Connection connection = DBManager.getConnection();
        String searchLog = "select * FROM user WHERE mail = ? and password = ?; ";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        User user = null;
        //int isItAdmin = user.getIsAdmin();
        try {
            preparedStatement = connection.prepareStatement(searchLog);
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() == false) {
                return user;
            }
            user = new User(resultSet.getInt("id"),resultSet.getString("mail"), resultSet.getString("password"), resultSet.getInt("isAdmin"));


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


    
    public Project createProject(Project project, int userID){
        Connection connection = DBManager.getConnection();
        String sqlstr = "INSERT INTO projects(name, description, numberOfEmployees, deadline, userID) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;
        try{
            preparedStatement = connection.prepareStatement(sqlstr);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setInt(3, project.getNumberOfEmployees());
            preparedStatement.setObject(4,  project.getDeadline());
            preparedStatement.setInt(5, userID);
            int row = preparedStatement.executeUpdate();
            System.out.println(row);
            System.out.println("Tillykke projekt: " + preparedStatement + " blev oprettet");
        } catch(SQLException sqlerror){
            System.out.println("Fejl i oprettelse af projekt=" + sqlerror);
        }
        return project;
    }
    public Subproject createSubProject(Subproject subproject, int userID){
        Connection connection = DBManager.getConnection();
        String sqlstr = "INSERT INTO subprojects(name, description, projectID) VALUES(?, ?, ?)";
        PreparedStatement preparedStatement;
        try{
            preparedStatement = connection.prepareStatement(sqlstr);
            preparedStatement.setString(1, subproject.getName());
            preparedStatement.setString(2, subproject.getDescription());
            preparedStatement.setInt(3, subproject.getProjectID());
            int row = preparedStatement.executeUpdate();
            System.out.println(row);
            System.out.println("Tillykke delprojekt: " + preparedStatement + " blev oprettet.");
        } catch(SQLException sqlerror){
            System.out.println("Fejl i oprettelse af delprojekt=" + sqlerror);
        }
        return subproject;
    }

    public Project getProjectByName(String name){
        try {
            Connection connection = DBManager.getConnection();
            String sqlproject = "SELECT * FROM projects WHERE name = '" + name + "'";
            PreparedStatement prepareStatement;
            prepareStatement = connection.prepareStatement(sqlproject);
            ResultSet resultSet = prepareStatement.executeQuery();

            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String projectName = resultSet.getString("name");
                String description = resultSet.getString("description");
                int numberOfEmployees = resultSet.getInt("numberOfEmployees");
                Date deadline = resultSet.getDate("deadlineDate"); // Grunden til det ikke virkede før, er at
                //Time deadlineTime = resultSet.getTime("currentTime");

                Project project = new Project(id, projectName, description, numberOfEmployees, deadline);
                return project;
            } else{
                return null;
            }
        } catch(SQLException exception){
            exception.printStackTrace();
            System.out.println("Fejl i nedhentning af projekter");
            return null;
        }
    }

    public ArrayList<Subproject> getSubprojects(int projectID, Project project1){
        ArrayList<Subproject> subprojectList = new ArrayList<>();
        try{
            Connection connection = DBManager.getConnection();
            String sqlSubproject = "SELECT * FROM subprojects WHERE projectID = \'" + projectID + "\'";
            PreparedStatement prepareStatement;
            prepareStatement = connection.prepareStatement(sqlSubproject);
            ResultSet resultSet = prepareStatement.executeQuery();
            System.out.println(resultSet);
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String subProjectname = resultSet.getString("name");
                String description = resultSet.getString("description");
                int projectID1 = resultSet.getInt("projectID");
                Project mainProject = project1;

                Subproject subproject = new Subproject(id, subProjectname, description, projectID1);
                subprojectList.add(subproject);
            }
        } catch (SQLException sqlerr){
            System.out.println("Fejl i underprojekter" + sqlerr);
        }
        return subprojectList;
    }

    public ArrayList<Project> getOneProject(Project project){
        ArrayList<Project> oneProject = new ArrayList<>();
        oneProject.add(project);
        return oneProject;
    }




    public ArrayList<Project> getProjects(){
        ArrayList<Project> projectList = null;
        try {
            Connection connection = DBManager.getConnection();
            ArrayList<Subproject> subprojects = null;
            Map<Integer,Project>  projectMap = new HashMap<>();
            String sqlsubproject = "SELECT * FROM subprojects sub JOIN" +
                    " projects proj ON proj.id= sub.projectID";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlsubproject);
            ResultSet resultSet = preparedStatement.executeQuery();
            String projectName =null;
            String description =null;
            int numberOfEmployees =0;
            int projectId = 0;
            Date deadline = null;
            Project project = null;
            while(resultSet.next()){
                projectId = resultSet.getInt("id");
                projectName = resultSet.getString("name");
                description = resultSet.getString("description");
                numberOfEmployees = resultSet.getInt("numberOfEmployees");
                deadline = resultSet.getDate("deadline");
                if(!projectMap.containsKey(projectId)){
                    int subID = resultSet.getInt("subId");
                    String subprojectName = resultSet.getString("subName");
                    String subprojectDes = resultSet.getString("subDescription");
                    int subprojectID = resultSet.getInt("projectID");
                    Subproject subproject = new Subproject(subID, subprojectName, subprojectDes, subprojectID);
                    subprojects = new ArrayList<>();
                    subprojects.add(subproject);
                    project = new Project(projectId, projectName, description, numberOfEmployees, deadline, subprojects);
                    projectMap.put(projectId,project);
                }else{
                    project = projectMap.get(projectId);
                    int subID = resultSet.getInt("subId");
                    String subprojectName = resultSet.getString("subName");
                    String subprojectDes = resultSet.getString("subDescription");
                    int subprojectID = resultSet.getInt("projectID");
                    project.getSubprojects().add(new Subproject(subID, subprojectName, subprojectDes, subprojectID));
                }


            }

            projectList = new ArrayList<>(projectMap.values());
            //}
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
