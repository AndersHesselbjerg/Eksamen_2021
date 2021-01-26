package com.example.demo.repositories;

import com.example.demo.models.Project;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {
    Mapper mapper = new Mapper();

    @Test
    public void testCon(){
        Connection connection = DBManager.getConnection();
    }

    @Test
    public void createUser() {
        Mapper mapper = new Mapper();
        User user = new User("test@newTest", "test");
        mapper.createUser(user);
    }


    @Test
    public void userExist() {
        String mail = "test@newTest";
        assertEquals(true, mapper.userExist(mail));
    }


    @Test
    public void createProject(){
        Mapper mapper = new Mapper();
        Project project = new Project(9, "Dette er en test fra admin 4", 9);
        mapper.createProject(project, 9);
    }

    @Test
    public void deleteProject(){
        Mapper mapper = new Mapper();
        Project project = new Project(39, "test2021", 9);
        mapper.deleteProject(39);
    }
}