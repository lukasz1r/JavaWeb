package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.data.CategoryData;
import com.project.repository.CategoryRepository;
import com.project.repository.NoteRepository;
import com.project.repository.UserRepository;

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
