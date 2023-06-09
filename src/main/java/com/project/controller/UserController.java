package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.data.CategoryData;
import com.project.data.NoteData;
import com.project.data.UserData;
import com.project.data.UsersRoles;
import com.project.repository.CategoryRepository;
import com.project.repository.NoteRepository;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;
import com.project.repository.UsersRolesRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    NoteRepository noteRepo;

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    UsersRolesRepository rolesRepo;

    @GetMapping("logged/")
    public String logged(HttpServletRequest request, Authentication authentication, HttpServletResponse response) {
        request.getSession().setAttribute("isLogged", true);
        UserData user = userRepo.findByEmail(authentication.getName());
        request.getSession().setAttribute("sessionUser", user.getName());
        request.getSession().setAttribute("id", user.getId());

        Cookie emailCookie = new Cookie("email", user.getEmail());
        emailCookie.setMaxAge(3600);
        response.addCookie(emailCookie);

        authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
                request.getSession().setAttribute("role", 1);
        } else if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_FULLUSER"))) {
                request.getSession().setAttribute("role", 2);
        } else if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"))) {
                request.getSession().setAttribute("role", 3);
        }

        if (request.getSession().getAttribute("role").equals(3)) {
            return "redirect:/sharedNotes/";
        }

        return "redirect:/notes/";
    }

    @GetMapping("home/")
    public String registrationForm(Model model) {
        ArrayList<NoteData> notes = noteRepo.findAll();
        model.addAttribute("notes", notes);
        ArrayList<CategoryData> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        return "userNotes";
    }

    @GetMapping("adminPanel/")
    public String adminPanel(Model model) {
        ArrayList<UserData> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "adminPanel";
    }

    @GetMapping("/userDelete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        noteRepo.deleteByUser_id(id);
        userRepo.deleteById(id);
        rolesRepo.deleteById(id);
        return "redirect:/adminPanel/";
    }
    
    @GetMapping("editUser/{email}")
    public String editUserForm(@PathVariable("email") String email, Model model) {
        UserData user = userRepo.findByEmail(email);
        model.addAttribute("user", user);
        String role = rolesRepo.getRole(email);
        model.addAttribute("role", role);
        return "editUser";
    }

    @PostMapping("editedUser/{email}")
    public String editUser(@PathVariable("email") String email, 
                           @RequestParam("emailCh") String emailCh,
                           @RequestParam("name") String name,
                           @RequestParam("role") String role) {
        UserData user = userRepo.findByEmail(email);
        user.setEmail(emailCh);
        user.setName(name);
        userRepo.save(user);

        UsersRoles roleTemp = rolesRepo.getRole(user.getId());
        if (role.equals("ADMIN")) {
            roleTemp.setRole_id((long) 1);
        } else if (role.equals("FULLUSER")) {   
            roleTemp.setRole_id((long) 2);
        } else if (role.equals("USER")) {
            roleTemp.setRole_id((long) 3);
        }
        rolesRepo.save(roleTemp);
        return "redirect:/adminPanel/";
    }
}
