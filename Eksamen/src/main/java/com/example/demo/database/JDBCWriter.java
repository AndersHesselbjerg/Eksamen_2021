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


    public User logIn(String user, String pass) {
        System.out.println("SÅ LANGT SÅ GODT ");
        Connection connection = DBManager.getConnection();
        String searchLog = "select mail, password FROM user WHERE mail = ? and password = ?; ";
        PreparedStatement preparedStatement;
        String mail = "";
        String password = "";
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
        } catch (SQLException sqlerr) {
            System.out.println("fejl i søgning = " + sqlerr.getMessage());
        }

        return u;
    }

    public Boolean userExist(String mail, String password) {
        Connection connection = DBManager.getConnection();
        String searchStr = "SELECT * FROM user where mail = ? and password = ? ";
        PreparedStatement preparedStatement;
        int res = 1;
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
                System.out.println("fundet antal = " + res);
            }
            if (res == 1) {
                exist = true;
            } else {
                System.out.println("fejl. antal fundne profiler: " + res);
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
            preparedStatement = connection.prepareStatement(sqlstr);
            preparedStatement.executeUpdate(sqlstr);
            System.out.println(preparedStatement);
        } catch(SQLException sqlerror){
            System.out.println("Fejl i oprettelse af projekt=" + sqlerror);
        }
    }

}
