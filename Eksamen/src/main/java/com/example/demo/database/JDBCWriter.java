package com.example.demo.database;

import com.example.demo.domain.Project;
import com.example.demo.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class JDBCWriter {
    /*

    public static User logIn(String user, String pass) {
        System.out.println("SÅ LANGT SÅ GODT ");
        Connection connection = DBManager.getConnection();
        String searchLog = "select count(*) as count, id, mail, password FROM user WHERE id = ? and mail = ? and password = ?; ";
        PreparedStatement preparedStatement;
        String mail = "";
        String password= "";
        User u = new User(mail, password);
        int res = -1;
        ResultSet resset;
        try {
            System.out.println("Prøver at logge ind");
            preparedStatement = connection.prepareStatement(searchLog);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            System.out.println(searchLog);
            System.out.println(preparedStatement);
            resset = preparedStatement.executeQuery();
            if (resset.next()) {
                String str = "" + resset.getObject(1);
                res = Integer.parseInt(str);
                System.out.println("fundet antal = " + res);
            }
            if (res == 1) {
                String id = "" + resset.getObject("id");
                String username = "" + resset.getObject("email");
                String userPassword = "" + resset.getObject("password");


                int idN = Integer.parseInt(id);

                System.out.println("vores id er = " + idN + " og som string " + id);


            }
        }catch (SQLException sqlerr) {
            System.out.println("fejl i søgning = " + sqlerr.getMessage());
        }

        return u;
    }
/*
    public void createNewProject(Project project){
        Connection connection = DBManager.getConnection();
        String sqlstr = "INSERT INTO projects(name, deadline, description, numberOfEmployees) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement;
        try{
            preparedStatement = connection.prepareStatement(sqlstr);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setDate(2, (java.sql.Date) project.getDeadline());
            preparedStatement.setString(3, project.getDescription());
            preparedStatement.setShort(4, project.getNumberOfEmployees());
            int row = preparedStatement.executeUpdate();
            System.out.println(row);
            System.out.println(preparedStatement);
        } catch(SQLException sqlerror){
            System.out.println("Fejl i oprettelse af projekt=" + sqlerror);
        }
    }

 */
}
