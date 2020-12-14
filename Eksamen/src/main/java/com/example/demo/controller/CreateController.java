package com.example.demo.controller;

import com.example.demo.database.Mapper;
import com.example.demo.domain.Project;
import com.example.demo.domain.Subproject;
import com.example.demo.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

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

    @GetMapping("/createUser")
    public String createUser(){
        return "createUser";
    }

    @PostMapping("/createProject")
    public String createProject(Project project, HttpSession session){
        User user = (User) session.getAttribute("login");
        int userid = user.getId();
        mapper.createProject(project, userid);
        checkLogin(user);
        System.out.println("Project created successfully");
        return "userProfile";
    }

    @PostMapping("/createSubProject")
    public String createSubProject(Subproject subproject, HttpSession session){
        User theuser = (User) session.getAttribute("login");
        int userid = theuser.getId();
        mapper.createSubProject(subproject, userid);

        System.out.println("Project created successfully");
        return "userProfile";
    }

    @PostMapping("updateProject")
    public String updateProject(@RequestParam Project project, HttpSession session){
        User theuser = (User) session.getAttribute("login");
        checkLogin(theuser);
        mapper.updateProject(project);
        return "userProfile";
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


    @GetMapping("updateProject")
    public String updateProject(){
        return "userProfile";
    }

    private void checkLogin(User user) {
        System.out.println("Bruger: " + user + ", er stadig logget ind! ");
    }

    private void setSessionInfo(WebRequest request, User user) {
        // Place user info on session
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
    }
}