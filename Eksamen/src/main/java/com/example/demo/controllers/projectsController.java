package com.example.demo.controllers;

import com.example.demo.models.Subproject;
import com.example.demo.models.User;
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
    public String getOneProject() {
        return "redirect:/project";
    }

    @GetMapping("/updateSubProject")
    public String updateSubProject(){
        return "userProfile";
    }

    @PostMapping("/updateSubProject")
    public String updateSub(Model model,Subproject subproject, HttpSession session){
        User user = (User) session.getAttribute("login");
        this.checkLogin(user);
        model.addAttribute("user");
        mapper.updateSubProject(subproject);
        return "subProject";
    }


    private void checkLogin(User user) {
        System.out.println("Bruger: " + user + ", er stadig logget ind! ");
    }
}
