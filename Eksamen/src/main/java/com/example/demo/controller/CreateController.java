package com.example.demo.controller;

import com.example.demo.database.JDBCWriter;
import com.example.demo.domain.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.Time;

@Controller
public class CreateController {

    Project project = new Project();
    JDBCWriter jdbcWriter = new JDBCWriter();

    @GetMapping("/createUser")
    public String creatUser(){
        return "createUser";
    }

    @GetMapping("/createProject")
    public String createProject() {
        return "createProject";
    }

    @GetMapping("/addEmployees")
    public String addEmployees() {
        return "addEmployees";
    }


    @PostMapping("/createProjectForm")
    public String createProjectForm(
            @ModelAttribute Project project,
            @RequestParam int id,
            @RequestParam String projectName,
            @RequestParam String description,
            @RequestParam LocalDate deadlineDate,
            @RequestParam Time deadlineTime) {
        System.out.println("Det virker!");
        Project project1 = new Project(id, projectName, description, deadlineDate, deadlineTime);
        jdbcWriter.createNewProject(project1);
        return "index";
    }



}