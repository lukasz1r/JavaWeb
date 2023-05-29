package com.example.demo.controllers;

import com.example.demo.data.UserData;
import com.example.demo.data.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
//
@Controller
public class UserController {

    @Autowired
    private UserRepository repo;

    @GetMapping("/u")
    public String getAllUsers(Model model){     
        List<UserData> users = repo.findAll();
        model.addAttribute("users", users);
        return "/users";
    }
}