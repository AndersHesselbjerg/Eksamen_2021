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

import java.util.Date;

@Controller
public class CreateController {

    Project project = new Project();
    JDBCWriter jdbcWriter = new JDBCWriter();

    @GetMapping("/createProject")
    public String createProject() {
        return "createProject";
    }

    @PostMapping("/addEmployees")
    public String addEmployees() {
        return "addEmployees";
    }

    @PostMapping("/createProjectForm")
    public String createProjectForm(
            @ModelAttribute Project project,
            @RequestParam int id,
            @RequestParam String projectName,
            @RequestParam Date deadline,
            @RequestParam String description,
            @RequestParam short numberOfEmployees
    ) {


        System.out.println("Det virker!");
        Project project1 = new Project(id, projectName, description, numberOfEmployees, deadline);
        jdbcWriter.createNewProject(project);
        return "addEmployees";
    }

}