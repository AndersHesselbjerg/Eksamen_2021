package com.example.demo.controller;

import com.example.demo.database.Mapper;
import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class CreateController {


    Mapper mapper;
    Project project;

    public CreateController(Mapper mapper){
        this.mapper = mapper;
    }



    @GetMapping("/createNewProject")
    public String showCreateProject(Project project, Model model, HttpSession session) {// Model model fletter data, og tager dem fra thymeleaf og bruger dem
        User theuser = (User) session.getAttribute("login");
        int userid = theuser.getIsAdmin();
        if (userid == 1){
            theuser.adminID++;
            model.addAttribute("project", project);
            return "createProject";
        } else {
            return "index";
        }
    }

    @GetMapping("/addEmployees")
    public String addEmployees() {
        return "addEmployees";
    }


    @GetMapping("/userProfile")
    public String userProfile(Model model, HttpServletRequest servletRequest) {
        ArrayList<Project> projectList = mapper.getUserProjects();
        model.addAttribute("project", projectList);
        HttpSession session = servletRequest.getSession();
        session.setAttribute("projectList",projectList);
        return "userProfile";
    }

    @GetMapping("/removeProject")
    public String removeProject(){
        return ("removeProject");
    }

    @GetMapping("updateProject")
    public String updateProject(){
        return "userProfile";
    }
}