package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.data.NoteData;
import com.example.demo.data.NoteRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class DodajWpisController {

    @Autowired
    private NoteRepository repo;

    @GetMapping("/dodaj-wpis")
        public String returnDodajWpis(){
            return "/dodaj-wpis";
        }

    @GetMapping("/dodaj-wpis-form")
    public String dodajWpisDoBazy(@RequestParam("title") String title, @RequestParam("content") String note, HttpSession session) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        NoteData noteData = new NoteData((int) repo.count() + 1, (Integer) session.getAttribute("id"), title, note, dateFormat.format(date));
        repo.save(noteData);
        return "/dodaj-wpis";
    }
}
