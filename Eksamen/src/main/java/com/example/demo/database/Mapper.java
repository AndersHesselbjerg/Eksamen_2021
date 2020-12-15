package com.example.demo.database;

import com.example.demo.domain.Project;
import com.example.demo.domain.Subproject;
import com.example.demo.domain.Task;
import com.example.demo.domain.User;
import org.springframework.stereotype.Repository;

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
        String sqlstr = "INSERT INTO subprojects(subName, subDescription, projectID) VALUES(?, ?, ?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sqlstr);
            preparedStatement.setString(1, subproject.getName());
            preparedStatement.setString(2, subproject.getDescription());
            preparedStatement.setInt(3, subproject.getProjectID());
            int row = preparedStatement.executeUpdate();
            System.out.println(row);
            System.out.println("Tillykke delprojekt: " + preparedStatement + " blev oprettet.");
        } catch (SQLException sqlerror) {
            System.out.println("Fejl i oprettelse af delprojekt=" + sqlerror);
        }
        return subproject;
    }

    public Project getProjectByName(String name) {
        try {
            Connection connection = DBManager.getConnection();
            String sqlproject = "SELECT * FROM projects WHERE name = '" + name + "'";
            PreparedStatement prepareStatement;
            prepareStatement = connection.prepareStatement(sqlproject);
            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String projectName = resultSet.getString("name");
                String description = resultSet.getString("description");
                int numberOfEmployees = resultSet.getInt("numberOfEmployees");
                Date deadline = resultSet.getDate("deadlineDate"); // Grunden til det ikke virkede før, er at
                //Time deadlineTime = resultSet.getTime("currentTime");
                Timestamp todaysDate = resultSet.getTimestamp("saved");

                Project project = new Project(id, projectName, description, numberOfEmployees, deadline, todaysDate);
                return project;
            } else {
                return null;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("Fejl i nedhentning af projekter");
            return null;
        }
    }

    public ArrayList<Subproject> getSubprojects(int projectID, Project project1) {
        ArrayList<Subproject> subprojectList = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        String sqlSubproject = "SELECT * FROM subprojects WHERE projectID = \'" + projectID + "\'";
        PreparedStatement prepareStatement;
        ResultSet resultSet;
        try {
            prepareStatement = connection.prepareStatement(sqlSubproject);
            resultSet = prepareStatement.executeQuery();
            System.out.println(resultSet);

            System.out.println(subprojectList);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String subProjectname = resultSet.getString("name");
                String description = resultSet.getString("description");
                int projectID1 = resultSet.getInt("projectID");

                Subproject subproject = new Subproject(id, subProjectname, description, projectID1);
                subprojectList.add(subproject);
            }
        } catch (SQLException sqlerr) {
            System.out.println("Fejl i underprojekter" + sqlerr);
        }
        return subprojectList;
    }

    public ArrayList<Project> getOneProject(int id) throws SQLException {
        ArrayList<Project> allprojects = new ArrayList<>();
        Connection connection = DBManager.getConnection();
        String sqlproject = "SELECT projects.id, projects.name, subprojects.subName, subprojects.subDescription, subprojects.subId, subprojects.projectID, projects.description, projects.numberOfEmployees, projects.deadline FROM projects left join subprojects on projects.id = subprojects.projectID WHERE id = \'" + id + "\'";
        PreparedStatement prepareStatement;
        prepareStatement = connection.prepareStatement(sqlproject);
        ResultSet resultSet = prepareStatement.executeQuery();
        System.out.println(resultSet);
        int projectID = resultSet.getInt("projects.id");
        String projectName = resultSet.getString("projects.name");
        String projectDes = resultSet.getString("projects.description");
        int numOfEmp = resultSet.getInt("projects.numberOfEmployees");
        Date deadline = resultSet.getDate("projects.deadline");
        Timestamp todaysDate = resultSet.getTimestamp("saved");
        Project project = new Project(projectID, projectName, projectDes, numOfEmp, deadline, todaysDate);
        allprojects.add(project);

        while (resultSet.next()) {
            int subID = resultSet.getInt("subprojects.id");
            String subName = resultSet.getString("subprojects.name");
            String subDes = resultSet.getString("subprojects.description");
            int subProjectID = resultSet.getInt("subprojects.projectID");
            Project subproject = new Project(subID, subName, subDes, subProjectID);
            allprojects.add(subproject);
        }


        return allprojects;


        //SELECT projects.name, subprojects.name, projects.description, projects.numberOfEmployees, projects.deadline,
        // FROM projects
        //left join subprojects on projects.id = subprojects.projectID
        //order by projects.name
        // Den skal først retuneere et objekt. tage et parameter som er et id. i metoden skal vi hente et objekt fra databasen. Hent et objekt fra databasen
        // hent alle subprojekter fra databasen og tilføj dem til arraylisten der ligger i det enkelte projekt.

    }

    Subproject subproject = new Subproject();

    public Project deleteSubProjectsOfProject(int projectID) {
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
                    Subproject subproject = new Subproject(subID, subprojectName, subprojectDes, subprojectID);
                    subprojects = new ArrayList<>();
                    subprojects.add(subproject);
                    int taskId = resultSet.getInt("taskID");
                    String taskName = resultSet.getString("taskName");
                    String taskDescription = resultSet.getString("taskDes");
                    Date taskDeadLine = resultSet.getDate("taskDeadline");
                    int projectIdTask = resultSet.getInt("projectIDTask");
                    Task task = new Task(taskId, taskName, taskDescription, taskDeadLine, projectIdTask);
                    tasks = new ArrayList<>();
                    tasks.add(task);
                    project = new Project(projectId, projectName, description, numberOfEmployees, deadline, subprojects, saved, tasks);
                    projectMap.put(projectId, project);
                } else {
                    project = projectMap.get(projectId);
                    int subID = resultSet.getInt("subId");
                    String subprojectName = resultSet.getString("subName");
                    String subprojectDes = resultSet.getString("subDescription");
                    int subprojectID = resultSet.getInt("projectID");

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
                    project.getSubprojects().add(new Subproject(subID, subprojectName, subprojectDes, subprojectID));
                    if (newTasks != null && newTasks.size() > 0) {
                        project.getTasks().addAll(newTasks);
                    }
                }


            }
            projectList=new ArrayList<>(projectMap.values());
        } catch (SQLException e) {
            System.out.println(e);
        }
        return projectList;
    }
}