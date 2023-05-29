package com.example.demo.controllers;

import com.example.demo.data.UserData;
import com.example.demo.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DodajWpisController {
    @GetMapping("/dodaj-wpis")
        public String returnDodajWpis(){
            return "/dodaj-wpis";
        }

    @GetMapping("/dodaj-wpis-form")
    public String dodajWpisDoBazy(){
        //miejsce odpowiedzialne za zapis wpisu do bazy
        return "/userNotes";
    }


}
