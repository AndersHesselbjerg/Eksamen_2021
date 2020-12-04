package com.example.demo.controller;

import com.example.demo.database.DBManager;
import com.example.demo.database.Mapper;
import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class myController {

    @Autowired
    Mapper mapper;

    @GetMapping("/")//her giver du data
    public String index() { // spring securaty!
        DBManager.getConnection();
        return "index";
    }

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

    @PostMapping("/login")//Her
    public String login(@RequestParam String mail, @RequestParam String password, WebRequest webRequest, Model model, HttpSession session){
        mail = webRequest.getParameter("mail");
        password = webRequest.getParameter("password");
        User user = mapper.logIn(mail, password);
        setSessionInfo(webRequest, user);
        ArrayList<Project> projects = mapper.getProjects();

        if(user == null){
            System.out.println("Der var intet match");
            return "redirect:/";
        } else {
            if(user.getIsAdmin() == 0) {
                session.setAttribute("login", user); // her add vi session
                System.out.println("User " + user + " er logget ind: ");
                model.addAttribute("projects", projects);
                return "userProfile";
            }

            else{
                System.out.println("Admin " + user + " er logget ind: ");
                return "admin";
            }
        }
    }
    /*@PostMapping("/admin")
    public String admin(@RequestParam String mail, @RequestParam String password, @RequestParam int isAdmin, int action, Model model){
        if(action == 1){
            System.out.println("Login tried!!!");
        }
        return "admin";
    }*/
    @GetMapping("/admin")
    public String admin(Model model){
        return "admin";
    }

    @PostMapping("removeUser")
    public String removeUser(@RequestParam int removeUser){
        mapper.deleteUser(removeUser);
        return "";
    }




    @PostMapping("/createProject")
    public String createProject(Project project, HttpSession session){
        /*
        //webRequest.getParameter("mail");
        //webRequest.getParameter("password");
        User theuser = (User) session.getAttribute("login");
        int userid = theuser.getId();
        mapper.createProject(project, userid);
        //setSessionInfo(webRequest, user);
         */
        System.out.println("Test gfesgfesg");
        return "userProfile";
    }

    @PostMapping("updateProject")
    public String updateProject(WebRequest webRequest, Project project, User user, HttpSession session){
        webRequest.getParameter("mail");
        webRequest.getParameter("password");
        User theuser = (User) session.getAttribute("login");
        int userid = theuser.getId();
        mapper.createProject(project, userid);
        setSessionInfo(webRequest, user);
        return "projects";


    }

    private void setSessionInfo(WebRequest request, User user) {
        // Place user info on session
        request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
    }
}
