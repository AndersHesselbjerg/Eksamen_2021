package com.example.demo.controllers;


import com.example.demo.repositories.DBManager;
import com.example.demo.repositories.Mapper;
import com.example.demo.models.Project;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class LoginController {
    @Autowired
    Mapper mapper;
    //Lavet af Alexander
    @GetMapping("/")
    public String index() {
        DBManager.getConnection();
        return "index";
    }
    //Lavet af Alexander
    @PostMapping("/login")
    public String login(@RequestParam String mail, @RequestParam String password, WebRequest webRequest, Model model, HttpSession session){
        mail = webRequest.getParameter("mail");
        password = webRequest.getParameter("password");
        User user = mapper.logIn(mail, password);
        setSessionInfo(webRequest, user);

        if(user == null){
            System.out.println("Der var intet match");
            return "redirect:/";
        } else {
            if(user.getIsAdmin() == 0) {
                session.setAttribute("login", user); // her add vi session
                System.out.println("User " + user + " er logget ind: ");
                ArrayList<Project> ps = mapper.getProjects();
                if (ps.size() > 0) {
                    model.addAttribute("projects", ps);
                }
                return "redirect:/userProfile";
            }
            session.setAttribute("login", user);
            System.out.println("Admin " + user + " er logget ind: ");
            return "redirect:/admin";
        }
    }
    //Lavet af Alexander
    @GetMapping("/logud")
    public String logud(HttpSession session){
        session.removeAttribute("login");
        return "index";
    }
    //Lavet af Alexander
    private void setSessionInfo(WebRequest request, User user) {
        // Place user info on session
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
    }



}
