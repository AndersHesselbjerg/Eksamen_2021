package com.example.demo.controller;

import com.example.demo.database.Mapper;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;


@Controller
public class removeController {
    @Autowired
    Mapper mapper;

    @GetMapping("/removeProject")
    public String removeProject(){
        return ("userProfile");
    }

    @GetMapping("/removeUser")
    public String removeuser(){
        return "removeUser";
    }

    @PostMapping("/removeUser")
    public String removeUser(@RequestParam int removeUser, HttpSession session){
        User user = (User) session.getAttribute("login");
        mapper.deleteUser(removeUser);
        checkLogin(user);
        return "removeUser";
    }

    @PostMapping("/removeProject")
    public String removeProject(@RequestParam int id, Model model, HttpSession session){
        User user = (User) session.getAttribute("login");
        checkLogin(user);
        model.addAttribute("user");
        mapper.deleteProject(id);
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
