package com.example.demo.controller;

import com.example.demo.database.JDBCWriter;
import com.example.demo.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;

@Controller
public class CreateController {

    @Autowired
    JDBCWriter jdbcWriter;
    Project project;

    @GetMapping("/createProject")
    public String showCreateProject(Project project, Model model) { // Model model fletter data, og tager dem fra thymeleaf og bruger dem
        model.addAttribute("project", project);
        return "createProject";
    }


    @GetMapping("/createUser")
    public String creatUser(){
        return "createUser";
    }

    @GetMapping("/addEmployees")
    public String addEmployees() {
        return "addEmployees";
    }

    @GetMapping("/userProfile")
    public String userProfile() {
        return "userProfile";
    }
}