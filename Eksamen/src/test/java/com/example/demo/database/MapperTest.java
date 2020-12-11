package com.example.demo.database;

import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

class MapperTest {
    Project project = new Project("Alexander","Dette er en test", 5, Date.valueOf("2020-10-10"));

    @Test
    void createUser() {
        Mapper mapper = new Mapper();
        User user = new User("a", "abc");
        mapper.createUser(user);
    }

    @Test
    void creatProject(){
        Mapper mapper = new Mapper();
        //mapper.createProject(project);
    }

    @Test
    void getProject(){
        Mapper mapper = new Mapper();
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(project);
        mapper.getUserProjects();

    }

}