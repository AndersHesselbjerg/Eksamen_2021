package com.example.demo.controllers;

import com.example.demo.repositories.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Project;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

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
        session.setAttribute("projects",projectList);
        return "projects";
    }

    @GetMapping("/project/{id}")
    public String project(@PathVariable("id") int id, Model model, HttpServletRequest servletRequest){
        HttpSession httpSession = servletRequest.getSession();

        ArrayList<Project> projectList = (ArrayList<Project>) httpSession.getAttribute("projects");
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
