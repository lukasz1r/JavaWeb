package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.data.CategoryData;
import com.example.demo.data.CategoryRepository;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/addCategory")
    public String showCategoryForm(Model model) {
        model.addAttribute("category", new CategoryData());
        return "add-category";
    }

    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute("category") CategoryData category) {
        categoryRepository.save(category);
        return "redirect:/home";
    }
}
