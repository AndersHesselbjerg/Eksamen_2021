package com.example.demo.controller;

import com.example.demo.database.DBManager;
import com.example.demo.database.JDBCWriter;
import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

@Controller
public class myController {

    @Autowired
    JDBCWriter jdbcWriter;

    @GetMapping("/")//her giver du data
    public String index(Model model, HttpSession session) {
        DBManager.getConnection();
        User user = (User)session.getAttribute("user"); // parantesen gør bare at den caster det som getAtribut returner om til User

        if (user != null){
            model.addAttribute("user", user);
        }
        return "index";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestParam String mail, @RequestParam String password, Model model, HttpSession session){
        Boolean userCheck = jdbcWriter.userExist(mail);
        if (userCheck == false){
            ArrayList<User> userList = new ArrayList<>();
            model.addAttribute("users", userList);
            User user = new User(mail, password);
            jdbcWriter.createUser(user);

            User user2 = (User)session.getAttribute("user");

            if (user2 != null){
                model.addAttribute("user", user2);
            }
            return "index";

        } else if (mail.isEmpty() || password.isEmpty()){
                return "createUser";
        } else {
            System.out.println("Denne mail eksisterer allerede");
            return "createUser";
        }
    }

    @PostMapping("/login")//Her
    public String login(@RequestParam String mail, @RequestParam String password, Model model, HttpSession session){
        User user = jdbcWriter.logIn(mail,password);
        ArrayList<Project> projects = jdbcWriter.getProjects();
        if(user == null){
            System.out.println("Der var intet match");
            return "redirect:/";
        } else {
            System.out.println("User " + user + " er logget ind: ");
            session.setAttribute("user", user);
            model.addAttribute("user", user);
            model.addAttribute("projects", projects);
            return "userProfile";
        }
    }

    @PostMapping("/createProject")
    public String createProject(Project project, Model model, HttpSession session){
            jdbcWriter.createProject(project);
        User user = (User)session.getAttribute("user"); // parantesen gør bare at den caster det som getAtribut returner om til User

        if (user != null){
            model.addAttribute("user", user);
        }
            return "userProfile";
    }
}
