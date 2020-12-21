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
    public String updateSub(@PathVariable("id") int id,Subproject subproject, HttpSession session){
        ArrayList<Subproject> projectList = (ArrayList<Subproject>) session.getAttribute("projectList");
        Subproject oneProject;
        for(Subproject subproject1: projectList){
            if(subproject.getId()==id){
                oneProject = subproject1;
                mapper.updateSubProject(subproject1);
            }
        }
        return "subProject";
    }




    private void checkLogin(User user) {
        System.out.println("Bruger: " + user + ", er stadig logget ind! ");
    }
}
