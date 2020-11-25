package com.example.demo.database;

import com.example.demo.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCWriter {

    public User logIn(String user, String pass) {
        Connection connection = DBManager.getConnection();
        String searchStr = "SELECT count(*) as count, user_id, email, password, name, surname, region, age, about, is_admin, image_link, gender_id FROM users WHERE email = ? and password = ?;";
        PreparedStatement preparedStatement;
        User u = new User();
        int res = -1;
        ResultSet resset;
        try {
            preparedStatement = connection.prepareStatement(searchStr);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            System.out.println(searchStr);
            System.out.println(preparedStatement);
            resset = preparedStatement.executeQuery();
            if (resset.next()) {
                String str = "" + resset.getObject(1);
                res = Integer.parseInt(str);
                System.out.println("fundet antal = " + res);
            }
            if (res == 1) {
                String id = "" + resset.getObject("user_id");
                String username = "" + resset.getObject("email");
                String age = "" + resset.getObject("age");
                String name = "" + resset.getObject("name");
                String surname = "" + resset.getObject("surname");
                String region = "" + resset.getObject("region");
                String about = "" + resset.getObject("about");
                String is_admin = "" + resset.getObject("is_admin");
                String imageLink = "" + resset.getObject("image_link");
                String gender_id = "" + resset.getObject("gender_id");

                int idN = Integer.parseInt(id);
                int ageN = Integer.parseInt(age);
                int gender_idN = Integer.parseInt(gender_id);
                Boolean isAdmin = false;

                System.out.println("vores id er = " + idN + " og som string " + id);

                if (is_admin.equals("1")){
                    isAdmin = true;
                }
                u = new User(idN, username,pass,name,surname,region, ageN,about,isAdmin, gender_idN, imageLink);
            } else {
                System.out.println("login fejl. antal fundne profiler: " + res);
            }

        } catch (SQLException sqlerr) {
            System.out.println("fejl i søgning = " + sqlerr.getMessage());
        }

        return u;
    }

}
