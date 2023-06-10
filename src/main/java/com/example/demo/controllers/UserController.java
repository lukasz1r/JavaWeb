package com.example.demo.controllers;

import com.example.demo.data.UserData;
import com.example.demo.data.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
//
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String getAllUsers(Model model){     
        List<UserData> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "/users";
    }

    @GetMapping("/userDelete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/editUser/{id}")
    public String editUserForm(@PathVariable("id") int id, Model model) {
        UserData user = userRepository.findById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") int id, @ModelAttribute("user") UserData editedUser) {
        UserData user = userRepository.findById(id);
        user.setUsername(editedUser.getUsername());
        user.setPassword(editedUser.getPassword());
        user.setRole(editedUser.getRole());
        userRepository.save(user);
        return "redirect:/users";
    }

}