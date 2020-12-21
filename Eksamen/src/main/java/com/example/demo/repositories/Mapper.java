package com.example.demo.repositories;

import com.example.demo.models.Project;
import com.example.demo.models.Subproject;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Repository
public class Mapper {

    public Mapper() {

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



    public ArrayList<Project> getProjects() {
        ArrayList<Project> projectList = new ArrayList<>();
        try {
            Connection connection = DBManager.getConnection();
            String sqlproject = "SELECT * FROM projects";
            PreparedStatement prepareStatement;
            prepareStatement = connection.prepareStatement(sqlproject);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String projectName = resultSet.getString("name");
                String description = resultSet.getString("description");
                int numberOfEmployees = resultSet.getInt("numberOfEmployees");
                Date deadline = resultSet.getDate("deadline"); // Grunden til det ikke virkede før, er at den skal heder deadline og ikke deadlinedate
                //Time deadlineTime = resultSet.getTime("currentTime");
                Timestamp todaysDate = resultSet.getTimestamp("saved");

                Project project = new Project(id, projectName, description, numberOfEmployees, deadline, todaysDate);
                projectList.add(project);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("Fejl i nedhentning af projekter");
        }
        return projectList;
    }


    public User deleteUser(int id) {
        Connection connection = DBManager.getConnection();
        String sqlRemove = "DELETE FROM user WHERE id = '?' ";
        PreparedStatement preparedStatement;
        String userIDstr = "" + id;
        User user = null;
        try {
            preparedStatement = connection.prepareStatement(sqlRemove);
            preparedStatement.setString(id, userIDstr);
            preparedStatement.execute(sqlRemove);
            System.out.println("Tillykke bruger: " + preparedStatement + " er blevet slettet");
        } catch (SQLException sqlerr) {
            System.out.println("Fejl =" + sqlerr);
        }
        return user;
    }

    public Project updateProject(Project project) {
        Connection connection = DBManager.getConnection();
        String sqlStr = "UPDATE projects SET name = ?, description = ? , numberOfEmployees = ?, saved = ?, userID = ? WHERE id = ? ";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setInt(3, project.getNumberOfEmployees());
            preparedStatement.setTimestamp(4, project.getSaved());
            preparedStatement.setArray(5, (Array) project.getUserID());
            int row = preparedStatement.executeUpdate();
            System.out.println(row);
            System.out.println("Tillykke brugeren: " + preparedStatement + " Er opdateret");

        } catch (SQLException sqlerr) {
            System.out.println("Fejl i opdate = " + sqlerr);
        }
        return project;
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
            user = new User(resultSet.getInt("id"), resultSet.getString("mail"), resultSet.getString("password"), resultSet.getInt("isAdmin"), resultSet.getInt("adminID"));


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


    public Project createProject(Project project, int userID) {
        Connection connection = DBManager.getConnection();
        String sqlstr = "INSERT INTO projects(name, description, numberOfEmployees, deadline, userID) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sqlstr);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setInt(3, project.getNumberOfEmployees());
            preparedStatement.setObject(4, project.getDeadline());
            preparedStatement.setInt(5, userID);
            int row = preparedStatement.executeUpdate();
            System.out.println(row);
            System.out.println("Tillykke projekt: " + preparedStatement + " blev oprettet");
        } catch (SQLException sqlerror) {
            System.out.println("Fejl i oprettelse af projekt=" + sqlerror);
        }
        return project;
    }


    public Subproject createSubProject(Subproject subproject, int userID) {
        Connection connection = DBManager.getConnection();
        String sqlstr = "INSERT INTO subprojects(subName, subDescription, projectID, estimatedTime) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sqlstr);
            preparedStatement.setString(1, subproject.getName());
            preparedStatement.setString(2, subproject.getDescription());
            preparedStatement.setInt(3, subproject.getProjectID());
            preparedStatement.setInt(4, subproject.getEstimatedTime());
            int row = preparedStatement.executeUpdate();
            System.out.println(row);
            System.out.println("Tillykke delprojekt: " + preparedStatement + " blev oprettet.");
        } catch (SQLException sqlerror) {
            System.out.println("Fejl i oprettelse af delprojekt=" + sqlerror);
        }
        return subproject;
    }

    public Subproject updateSubProject(Subproject subproject){
        Connection connection = DBManager.getConnection();
        String SqlStr = "update subProjects set subName = ?, subDescription = ?, projectID = ?, estimatedTime = ? where subId = ? ";
        PreparedStatement preparedStatement;
        try{
            preparedStatement = connection.prepareStatement(SqlStr);
            preparedStatement.setString(1, subproject.getName());
            preparedStatement.setString(2, subproject.getDescription());
            preparedStatement.setInt(3, subproject.getProjectID());
            preparedStatement.setInt(4,subproject.getEstimatedTime());
            int row = preparedStatement.executeUpdate();
            System.out.println("Antal rækker: " + row);
            System.out.println("Tillykke update succesfully sub projekt til: " + preparedStatement);


        } catch (SQLException sqlErr){
            System.out.println("Fejl i update af subproject: = " + sqlErr);
        }
        return subproject;
    }

    public int getLastProjectID()  {
        Connection connection = DBManager.getConnection();
        String sqlstr = "SELECT max(id) FROM projects";
        int lastProjectID = 0;
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(sqlstr);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                lastProjectID = resultSet.getInt("max(id)");
            }
        } catch (SQLException sqlerr){
            System.out.println("Kunne ikke finde det sidste projekts id");
        }
        return lastProjectID;
    }


    //Hvis delete task ikke bliver færdigt, slettes denne metode
    public Project deleteTaskOfProject(int projectID){
        Connection connection = DBManager.getConnection();
        String sqlStr = "Delete from tasks where projectIDTask = ?";

        PreparedStatement preparedStatement;

        Project project = null;
        try {
            preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setInt(1, projectID);
            preparedStatement.execute();
            System.out.println("Tillykke  task: " + preparedStatement + " er blevet slettet");

        } catch (SQLException sqlerr) {
            System.out.println("Fejl = " + sqlerr);
        }
        return project;
    }

    public Project deleteSubProjectsOfProject(int projectID) {
        this.deleteTaskOfProject(projectID);

        Connection connection = DBManager.getConnection();
        String sqlStr = "Delete from subprojects where projectID = ? ";
        PreparedStatement preparedStatement;

        Project project = null;
        try {
            preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setInt(1, projectID);
            preparedStatement.execute();
            System.out.println("Tillykke  subproject: " + preparedStatement + " er blevet slettet");

        } catch (SQLException sqlerr) {

            System.out.println("Fejl =" + sqlerr);
        }
        return project;
    }



    public Project deleteProject(int projectID) {

        this.deleteSubProjectsOfProject(projectID);

        Connection connection = DBManager.getConnection();
        String sqlStr = "Delete from projects where id = ? ";
        PreparedStatement preparedStatement;

        Project project = null;
        try {
            preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.setInt(1, projectID);


            preparedStatement.execute();
            System.out.println("Tillykke project: " + preparedStatement + " er blevet slettet");

        } catch (SQLException sqlerr) {

            System.out.println("Fejl =" + sqlerr);
        }
        return project;
    }

    public ArrayList<Project> getUserProjects() {
        ArrayList<Project> projectList = null;
        try {
            Connection connection = DBManager.getConnection();
            ArrayList<Subproject> subprojects = null;
            ArrayList<Task> tasks = null;
            Map<Integer, Project> projectMap = new HashMap<>();
            String sqlsubproject = "SELECT * FROM subprojects sub JOIN" +
                    " projects proj ON proj.id= sub.projectID" +
                    " LEFT OUTER JOIN tasks task ON task.projectIDTask = proj.id";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlsubproject);
            ResultSet resultSet = preparedStatement.executeQuery();
            String projectName = null;
            String description = null;
            int numberOfEmployees = 0;
            int projectId = 0;
            Date deadline = null;
            Project project = null;
            Timestamp saved = null;
            int totalEstimatedTime = 0;
            while (resultSet.next()) {
                projectId = resultSet.getInt("id");
                projectName = resultSet.getString("name");
                description = resultSet.getString("description");
                numberOfEmployees = resultSet.getInt("numberOfEmployees");
                deadline = resultSet.getDate("deadline");
                saved = resultSet.getTimestamp("saved");
                if (!projectMap.containsKey(projectId)) {
                    int subID = resultSet.getInt("subId");
                    String subprojectName = resultSet.getString("subName");
                    String subprojectDes = resultSet.getString("subDescription");
                    int subprojectID = resultSet.getInt("projectID");
                    int estimatedTime = resultSet.getInt("estimatedTime");
                    Subproject subproject = new Subproject(subID, subprojectName, subprojectDes, subprojectID, estimatedTime);
                    subprojects = new ArrayList<>();
                    subprojects.add(subproject);
                    totalEstimatedTime += estimatedTime;
                    int taskId = resultSet.getInt("taskID");
                    String taskName = resultSet.getString("taskName");
                    String taskDescription = resultSet.getString("taskDes");
                    Date taskDeadLine = resultSet.getDate("taskDeadline");
                    int projectIdTask = resultSet.getInt("projectIDTask");
                    Task task = new Task(taskId, taskName, taskDescription, taskDeadLine, projectIdTask);
                    tasks = new ArrayList<>();
                    tasks.add(task);
                    project = new Project(projectId, projectName, description, numberOfEmployees, deadline, subprojects, saved, tasks, totalEstimatedTime);
                    projectMap.put(projectId, project);
                } else {
                    project = projectMap.get(projectId);
                    int subID = resultSet.getInt("subId");
                    String subprojectName = resultSet.getString("subName");
                    String subprojectDes = resultSet.getString("subDescription");
                    int subprojectID = resultSet.getInt("projectID");
                    int estimatedTime = resultSet.getInt("estimatedTime");
                    totalEstimatedTime += estimatedTime;

                    int taskId = resultSet.getInt("taskID");
                    String taskName = resultSet.getString("taskName");
                    String taskDescription = resultSet.getString("taskDes");
                    Date taskDeadLine = resultSet.getDate("taskDeadline");
                    int projectIdTask = resultSet.getInt("projectIDTask");
                    ArrayList<Task> tasksFromMap = project.getTasks();
                    ArrayList<Task> newTasks = new ArrayList<>();
                    Task newTask = new Task(taskId, taskName, taskDescription, taskDeadLine, projectIdTask);
                    boolean newTaskFound = false;
                    for (Task task : tasksFromMap) {
                        newTaskFound = false;
                        if (task.getId() != newTask.getId()) {
                            newTaskFound = true;
                        }
                    }
                    if (newTaskFound) {
                        newTasks.add(new Task(taskId, taskName, taskDescription, taskDeadLine, projectIdTask));
                    }
                    project.getSubprojects().add(new Subproject(subID, subprojectName, subprojectDes, subprojectID, estimatedTime));
                    project.setTotalEstimatedTime(totalEstimatedTime);
                    totalEstimatedTime = 0;

                    if (newTasks != null && newTasks.size() > 0) {
                        project.getTasks().addAll(newTasks);
                    }
                }


            }
            projectList = new ArrayList<>(projectMap.values());
        } catch (SQLException e) {
            System.out.println(e);
        }
        return projectList;
    }
}