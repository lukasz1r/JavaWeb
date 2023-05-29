package com.example.demo.controllers;

import com.example.demo.data.NoteData;
import com.example.demo.data.NoteRepository;
import com.example.demo.data.UserData;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NoteController {

    @Autowired
    private NoteRepository noteRepo;

    @Autowired
    private NoteRepository userRepo;

    @GetMapping("/n")
    public String getAllNotes(Model model){     
        List<NoteData> notes = noteRepo.findAll();
        model.addAttribute("notes", notes);
        return "/notes";
    }

    @GetMapping("/home")
    public String getAllNotesHomePage(Model model) {
        List<NoteData> notes = userRepo.findAll();
        model.addAttribute("notes", notes);
        return "/index";
    }

    @GetMapping("/notes")
    public String getAllNotesUser(Model model) {
        List<NoteData> notes = userRepo.findAll();
        model.addAttribute("notes", notes);
        return "/userNotes";
    }
}