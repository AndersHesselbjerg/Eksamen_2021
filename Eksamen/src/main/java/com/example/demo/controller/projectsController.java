package com.example.demo.controller;

import com.example.demo.database.DBManager;
import com.example.demo.database.Mapper;
import com.example.demo.domain.Subproject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.domain.Project;

import java.sql.Connection;
import java.util.ArrayList;

@Controller
public class projectsController {

    Mapper mapper = new Mapper();

    @GetMapping("/subProjects")
    public String subProjects(){
        return "subProjects";
    }


    @GetMapping("/projects")
    public String projects(Model model){
        ArrayList<Project> projectList = mapper.getProjects();
        model.addAttribute("project", projectList);
        return "projects";
    }
    @PostMapping("/project")
    public String project(@RequestParam Project project, Model model){
        return "project";
    }
     
}
