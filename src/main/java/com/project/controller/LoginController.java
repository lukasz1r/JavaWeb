package com.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.data.RoleData;
import com.project.data.UserData;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        UserData user = new UserData();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/login";
    }

    @PostMapping("/registration")
    public String registration(
            @Valid @ModelAttribute("user") UserData userData,
            BindingResult result,
            Model model) {
            UserData existingUser = userRepository.findByEmail(userData.getEmail());

        if (existingUser != null) {
            result.rejectValue("email", null, "Użytkownik istnieje");
            return "/registration";
            
        } else if (existingUser == null) {
            RoleData role = roleRepository.findByName("ROLE_USER");
            UserData user = new UserData(userData.getName(), userData.getEmail(), passwordEncoder.encode(userData.getPassword()), List.of(role));
            userRepository.save(user);
        }

        return "redirect:/registration?success";
    }
}
