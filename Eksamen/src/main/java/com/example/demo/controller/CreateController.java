package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CreateController {

    @GetMapping("createProject")
    public String createProject() {
        return "createProject";
    }

    @PostMapping("createProjectForm")
    public String createProject(
            @
    )

}
