package com.example.demo.controller;

import com.example.demo.database.DBManager;
import com.example.demo.database.Mapper;
import com.example.demo.domain.Subproject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.domain.Project;
import org.springframework.web.context.request.WebRequest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class projectsController {

    Mapper mapper = new Mapper();

    @GetMapping("/subProjects")
    public String subProjects(){
        return "subProjects";
    }

    @PostMapping("/subproject")
    public String subProject(){
        return "";
    }


    @GetMapping("/projects")
    public String projects(Model model) throws SQLException {
        ArrayList<Project> projectList = mapper.getOneProject(3);
        /*for (Project project : projectList) {
            ArrayList<Subproject> thisSubproject = mapper.getSubprojects(project.getId(), project);
            subProjectList.addAll(thisSubproject);
        }*/
        model.addAttribute("project", projectList);
        //model.addAttribute("subProject", projectList);
        return "projects";
    }

    /*@PostMapping("/project")
    public String project(@RequestParam Project project, Model model){
        ArrayList<Project> oneProject = mapper.getOneProject(project);
        model.addAttribute("project", oneProject);
        return "project";
    }*/
    @PostMapping("/getOneProject")
    public String getOneProject(WebRequest request,
                                @RequestParam String name,
                                @RequestParam String description,
                                @RequestParam int numberOfEmployees,
                                @RequestParam Date deadline) {
        return "redirect:/project";
    }
}
