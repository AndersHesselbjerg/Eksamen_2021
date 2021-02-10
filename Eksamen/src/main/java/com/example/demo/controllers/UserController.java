package com.example.demo.controllers;

import com.example.demo.repositories.DBManager;
import com.example.demo.repositories.Mapper;
import com.example.demo.models.Project;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class UserController {

    @Autowired
    Mapper mapper;
    //Lavet af Daniel
    @GetMapping("/userProfile") //post mapping er: creatNewProject og removeProject
    public String userProfile(Model model, HttpServletRequest servletRequest) {
        ArrayList<Project> projectList = mapper.getUserProjects();
        model.addAttribute("project", projectList);
        HttpSession session = servletRequest.getSession();
        session.setAttribute("projectList",projectList);
        return "userProfile";
    }
    //Lavet af Alexander
    @PostMapping("/createUser")
    public String createUser(@RequestParam String mail, @RequestParam String password, Model model){
        Boolean userCheck = mapper.userExist(mail);
        if (userCheck == false){
            ArrayList<User> userList = new ArrayList<>();
            model.addAttribute("users", userList);
            User user = new User(mail, password);
            mapper.createUser(user); 
            return "index";

        } else if (mail.isEmpty() || password.isEmpty()){
                return "createUser";
        } else {
            System.out.println("Denne mail eksisterer allerede");
            return "createUser";
        }
    }

    @GetMapping("/admin")
    public String admin(Model model){
        return "admin";
    }


    private void checkLogin(User user) {
        System.out.println("Bruger: " + user + ", er stadig logget ind! ");
    }

    private void setSessionInfo(WebRequest request, User user) {
        // Place user info on session
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
    }
}

