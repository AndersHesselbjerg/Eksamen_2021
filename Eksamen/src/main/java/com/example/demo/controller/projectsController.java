package com.example.demo.controller;

import com.example.demo.database.DBManager;
import com.example.demo.database.Mapper;
import com.example.demo.domain.Subproject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.example.demo.domain.Project;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class projectsController {

    Mapper mapper = new Mapper();
    @GetMapping("/subProjects")
    public String subProjects(){
        return "subProjects";
    }

    @GetMapping("/projects")
    public String projects(Model model, HttpServletRequest servletRequest) {
        ArrayList<Project> projectList = mapper.getUserProjects();
        model.addAttribute("project", projectList);
        HttpSession session = servletRequest.getSession();
        session.setAttribute("projectList",projectList);
        return "projects";
    }

    @GetMapping("/project/{id}")
    public String project(@PathVariable("id") int id, Model model, HttpServletRequest servletRequest){
        HttpSession httpSession = servletRequest.getSession();

        ArrayList<Project> projectList = (ArrayList<Project>) httpSession.getAttribute("projectList");
        Project oneProject = null;
        for(Project project:projectList){
            if(project.getId()==id){
                oneProject = project;
            }
        }
        model.addAttribute("project", oneProject);
        return "project";
    }


    @PostMapping("/getOneProject")
    public String getOneProject(WebRequest request,
                                @RequestParam String name,
                                @RequestParam String description,
                                @RequestParam int numberOfEmployees,
                                @RequestParam Date deadline) {
        return "redirect:/project";
    }
}
