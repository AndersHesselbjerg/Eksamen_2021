package com.example.demo.database;

import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class JDBCWriterTest {

    @Test
    void createUser() {
        JDBCWriter jdbcWriter = new JDBCWriter();
        User user = new User("a", "abc");
        jdbcWriter.createUser(user);
    }

    @Test
    void creatProject(){
        JDBCWriter jdbcWriter = new JDBCWriter();
        Project project = new Project("Alexander","Dette er en test", 5);
        jdbcWriter.createProject(project);
    }

}