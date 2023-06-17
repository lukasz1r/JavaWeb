package com.project.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.data.CategoryData;
import com.project.data.NoteData;
import com.project.repository.CategoryRepository;
import com.project.repository.NoteRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class AddNoteController {

     @Autowired
     private NoteRepository noteRepo;

     @Autowired
     private CategoryRepository categoryRepo;

     @GetMapping("/addNote")
     public String returnDodajWpis(Model model){
          List<CategoryData> categories = categoryRepo.findAll();
          model.addAttribute("categories", categories);
          return "/addNote";
     }

     @GetMapping("/addNoteForm")
     public String dodajWpisDoBazy(@RequestParam("title") String title, @RequestParam("content") String note, @RequestParam("category_id") int category_id, @RequestParam("remind_date") String remind_date, HttpSession session) {
          noteRepo.save(new NoteData(
                         noteRepo.count() + 1, 
                         (Long) session.getAttribute("id"), 
                         title, 
                         note, 
                         (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()), 
                         remind_date,
                         category_id));
          return "redirect:/notes/";
     }
}