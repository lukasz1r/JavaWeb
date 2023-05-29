package com.example.demo.controllers;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.data.UserData;
import com.example.demo.data.UserRepository;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private UserRepository repo;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logged")
    public String logged(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        UserData user = repo.findByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                session.setAttribute("isLogged", true);
                session.setAttribute("username", username);
                return "redirect:/home";
            }
        }
        return "redirect:/login/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }
}