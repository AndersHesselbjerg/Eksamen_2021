package com.example.demo.models;

import com.example.demo.repositories.Mapper;

import javax.servlet.http.HttpSession;

public class SuperProject {

    Mapper mapper = new Mapper();

    private boolean checkIfAdmin(){
        return true;
    }

}
