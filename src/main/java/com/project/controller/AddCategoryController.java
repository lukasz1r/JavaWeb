package com.project.controller;

import org.hibernate.mapping.List;
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
import com.project.repository.CategoryRepository;
import com.project.repository.NoteRepository;
import com.project.repository.UserRepository;

import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class AddCategoryController {

     @Autowired
     NoteRepository noteRepo;

     @Autowired
     CategoryRepository categoryRepo;

     @Autowired
     UserRepository userRepo;

     @PostMapping("addCategory/")
     public String addCategory() {
          
          return "addCategory";
     }

     @GetMapping("addCategoryForm/")
     public String addCategoryForm(@RequestParam("name") String name) {
          CategoryData category = new CategoryData((int) categoryRepo.count() + 1, name);
          categoryRepo.save(category);
          return "redirect:/home/";
     }
}
