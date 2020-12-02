package com.example.demo.database;

import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JDBCWriterTest {
    Project project = new Project("Alexander","Dette er en test", 5, Date.valueOf("2020-10-10"));

    @Test
    void createUser() {
        JDBCWriter jdbcWriter = new JDBCWriter();
        User user = new User("a", "abc");
        jdbcWriter.createUser(user);
    }

    @Test
    void creatProject(){
        JDBCWriter jdbcWriter = new JDBCWriter();
        jdbcWriter.createProject(project);
    }

    @Test
    void getProject(){
        JDBCWriter jdbcWriter = new JDBCWriter();
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(project);
        jdbcWriter.getProjects();

    }

}