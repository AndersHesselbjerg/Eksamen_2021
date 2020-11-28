package com.example.demo.controller;

import com.example.demo.database.DBManager;
import com.example.demo.database.JDBCWriter;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

@Controller
public class myController {

    @Autowired
    JDBCWriter jdbcWriter;

    @GetMapping("/")//her giver du data
    public String index() {
        DBManager.getConnection();
        return "index";
    }

    @PostMapping("/login")//Her
    public String login(@RequestParam String mail, @RequestParam String password){
        User user = jdbcWriter.logIn(mail,password);

        if(user == null){
            System.out.println("Der var intet match");
            return "redirect:/";
        } else {
            System.out.println("User " + user + " er logget ind: ");
            return "createProject";
        }
    }

    @PostMapping("/createUser")
    public String createUser(@RequestParam String mail, @RequestParam String password, Model model){
        ArrayList<User> userList = new ArrayList<>();
        model.addAttribute("user", userList);
        User u = new User(mail, password);
        jdbcWriter.createUser(u);
        return "index";
    }

}
