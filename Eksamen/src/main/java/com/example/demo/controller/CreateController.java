package com.example.demo.controller;

import com.example.demo.database.Mapper;
import com.example.demo.domain.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class CreateController {


    Mapper mapper;
    Project project;

    public CreateController(Mapper mapper){
        this.mapper = mapper;
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

    @GetMapping("RemoveUser")
    public String removeUser(){
        return "removeUser";
    }

    @GetMapping("/addEmployees")
    public String addEmployees() {
        return "addEmployees";
    }

    @GetMapping("/userProfile")
    public String userProfile(Model model) {
        ArrayList<Project> projects = new ArrayList<>();
        projects = mapper.getProjects();
        model.addAttribute("projects", projects);
        return "userProfile";

    }
}