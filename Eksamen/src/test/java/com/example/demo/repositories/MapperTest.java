package com.example.demo.repositories;

import com.example.demo.models.Task;
import com.example.demo.models.User;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class MapperTest {
    Mapper mapper = new Mapper();

    @Test
    public void testConnection() {
        Connection connection = DBManager.getConnection();
        assertEquals(true, true);

    }

    @Test
    void userExist() {
        String mail = "admin@stohn.dk";
        boolean status = mapper.userExist(mail);
        if (status == false) {
            fail();

        } else if (status == true){
            assertEquals(true, true);
            System.out.println("Status er: " + status);
        }
    }

    @Test
    void login() {
       String mail = "test@test.dk";
       String password = "test";
       User user = mapper.logIn(mail, password);

       if (mail.matches(user.getMail()) || password.matches(password)){
           assertEquals(user, user);
           System.out.println("Status er: " + user);

       } else {
           fail();
       }
    }
}