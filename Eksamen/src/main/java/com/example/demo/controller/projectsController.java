package com.example.demo.controller;

import com.example.demo.database.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.domain.Project;

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
     
}
