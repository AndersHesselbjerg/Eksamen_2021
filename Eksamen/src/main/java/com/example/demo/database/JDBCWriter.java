package com.example.demo.database;

import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


@Repository
public class JDBCWriter {

    public void createUser(User u) {
        Connection connection = DBManager.getConnection();
        String sqlstr = "INSERT INTO user (mail, password ) VAlUES (?, ?);";
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

    public Boolean userExist(String mail, String password) {

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
    
    public void createNewProject(Project project){
        /*LocalDate temp = project.getDeadlineDate();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String s = format.format(temp);
        Date date = Date.valueOf(temp);*/

        System.out.println("Så langt så godt");
        Connection connection = DBManager.getConnection();
        String sqlstr = "INSERT INTO projects(name, deadlineDate, DeadlineTime, description) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStatement;
        try{
            preparedStatement = connection.prepareStatement(sqlstr);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setObject(2,  project.getDeadlineDate());
            preparedStatement.setTime(3, project.getDeadlineTime());
            preparedStatement.setString(4, project.getDescription());
            //preparedStatement.setDate(2, s.);
            //int row = preparedStatement.executeUpdate();
            preparedStatement.executeUpdate(sqlstr);
            System.out.println(preparedStatement);
        } catch(SQLException sqlerror){
            System.out.println("Fejl i oprettelse af projekt=" + sqlerror);
        }
    }

}
