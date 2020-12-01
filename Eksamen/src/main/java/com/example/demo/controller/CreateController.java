package com.example.demo.controller;

import com.example.demo.database.JDBCWriter;
import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

@Controller
public class CreateController {


    JDBCWriter jdbcWriter;
    Project project;

    public CreateController(JDBCWriter jdbcWriter){
        this.jdbcWriter = jdbcWriter;
    }

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
    public String userProfile(Model model) {
        ArrayList<Project> projects = new ArrayList<>();
        projects = jdbcWriter.getProjects();
        model.addAttribute("projects", projects);
        return "userProfile";

    }
}