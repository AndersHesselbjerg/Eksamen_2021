package com.example.demo.controller;

import com.example.demo.database.DBManager;
import com.example.demo.database.JDBCWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.domain.Project;

import java.sql.Connection;
import java.util.ArrayList;

@Controller
public class projectsController {

    JDBCWriter jdbcWriter = new JDBCWriter();

    @GetMapping("/subProjects")
    public String subProjects(){
        return "subProjects";
    }


    @GetMapping("/projects")
    public String projects(Model model){
        ArrayList<Project> projectList = jdbcWriter.getProjects();
        model.addAttribute("project", projectList);
        return "projects";
    }
     
}
